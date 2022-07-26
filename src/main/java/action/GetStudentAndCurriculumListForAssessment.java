package action;

import dao.*;
import dao.impl.*;
import entity.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetStudentAndCurriculumListForAssessment implements Action {

    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private LevelDAO levelDAO = new LevelDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();
    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID));
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        String subjectName = request.getParameter(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        String levelName = request.getParameter(LEVEL);
        Level level = levelDAO.getLevelByLevelName(levelName);
        List<Student> studentList = studentDAO.getListOfStudentsByLevelName(levelName);
        List<CurriculumRecord> recordList = curriculumRecordDAO.getAllRecordsByLevelSubjectAndTeacher(level, subject, teacher);
        String shortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(shortName);
        if(locale == null){
            locale=Locale.getDefault();
        }
        Subject localeSubject = subjectLocaleDAO.getSubjectLocaleBySubjectAndLocale(subject,locale);
        session.setAttribute(SUBJECT, localeSubject);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(LEVEL, level);
        session.setAttribute(STUDENT_LIST, studentList);
        session.setAttribute(RECORD_LIST, recordList);
        request.getRequestDispatcher(ADD_STUDENT_ASSESSMENT_JSP).forward(request, response);

    }
}
