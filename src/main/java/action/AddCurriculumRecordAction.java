package action;

import constants.JSPPagesNameConstants;
import dao.*;
import daoImpl.*;
import entity.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddCurriculumRecordAction implements Action{

    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private LevelDAO levelDAO = new LevelDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String date = request.getParameter(LESSON_DATE);
        LocalDate lessonDate = LocalDate.parse(date);
        String subjectName = request.getParameter(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID));
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        String levelName = request.getParameter(LEVEL);
        Level level = levelDAO.getLevelByLevelName(levelName);
        String lessonTheme = request.getParameter(LESSON_THEME);
        String homeTask = request.getParameter(HOME_TASK);
        CurriculumRecord curriculumRecord = new CurriculumRecord(lessonDate,subject,level, teacher,lessonTheme, homeTask );
        curriculumRecordDAO.addCurriculumRecord(curriculumRecord);
        List<CurriculumRecord> recordList = curriculumRecordDAO.getAllRecordsByLevelSubjectAndTeacher(level, subject, teacher);
        String shortName = (String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(shortName);
        if(locale==null){
            locale = Locale.getDefault();
        }
        Subject subjectLocale = subjectLocaleDAO.getSubjectLocaleBySubjectAndLocale(subject, locale);
        session.setAttribute(RECORD_LIST, recordList);
        session.setAttribute(SUBJECT, subjectLocale);
        session.setAttribute(LEVEL, level);
        session.setAttribute(TEACHER, teacher);
        request.getRequestDispatcher(CURRICULUM_RECORD_LIST_JSP).forward(request, response);
    }
}
