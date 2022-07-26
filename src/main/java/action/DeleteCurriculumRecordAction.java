package action;

import dao.CurriculumRecordDAO;
import dao.SubjectLocaleDAO;
import dao.impl.CurriculumRecordDAOImpl;
import dao.impl.SubjectLocaleDAOImpl;
import entity.CurriculumRecord;
import entity.Level;
import entity.Subject;
import entity.Teacher;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteCurriculumRecordAction implements Action {

    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long recordId = Long.parseLong(request.getParameter(RECORD_ID));
        curriculumRecordDAO.deleteCurriculumRecord(recordId);
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute(TEACHER);
        Subject subjectLocale = (Subject) session.getAttribute(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectLocale.getName());
        Level level = (Level) session.getAttribute(LEVEL);
        List<CurriculumRecord> recordList = curriculumRecordDAO.getAllRecordsByLevelSubjectAndTeacher(level, subject, teacher);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(RECORD_LIST, recordList);
        session.setAttribute(SUBJECT, subjectLocale);
        request.getRequestDispatcher(CURRICULUM_RECORD_LIST_JSP).forward(request, response);


    }
}
