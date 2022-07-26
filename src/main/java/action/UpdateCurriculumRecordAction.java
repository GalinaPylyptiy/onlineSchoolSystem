package action;

import dao.CurriculumRecordDAO;
import dao.SubjectLocaleDAO;
import dao.impl.CurriculumRecordDAOImpl;
import dao.impl.SubjectLocaleDAOImpl;
import entity.CurriculumRecord;
import entity.Level;
import entity.Subject;
import entity.Teacher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class UpdateCurriculumRecordAction implements Action {

    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long recordId = Long.parseLong(request.getParameter(RECORD_ID));
        Teacher teacher = (Teacher)session.getAttribute(TEACHER);
        Subject subjectLocale = (Subject) session.getAttribute(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectLocale.getName());
        Level level = (Level) session.getAttribute(LEVEL);
        String date = request.getParameter(LESSON_DATE);
        LocalDate lessonDate = LocalDate.parse(date);
        String lessonTheme = request.getParameter(LESSON_THEME);
        String homeTask = request.getParameter(HOME_TASK);
        CurriculumRecord newRecord = new CurriculumRecord(lessonDate, subject, level, teacher, lessonTheme, homeTask);
        curriculumRecordDAO.updateCurriculumRecord(newRecord, recordId);
        List<CurriculumRecord> recordList = curriculumRecordDAO.getAllRecordsByLevelSubjectAndTeacher(level, subject, teacher);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(RECORD_LIST, recordList);
        session.setAttribute(SUBJECT, subjectLocale);
        request.getRequestDispatcher(CURRICULUM_RECORD_LIST_JSP).forward(request, response);
    }
}
