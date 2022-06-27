package action;
import dao.*;
import daoImpl.*;
import entity.Locale;
import entity.Subject;
import entity.Teacher;
import entity.TeacherType;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import util.Security;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRegisterAction implements Action {

    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private TeacherTypeLocaleDAO teacherTypeLocaleDAO = new TeacherTypeLocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private TeacherSubjectListDAO teacherSubjectDAO = new TeacherSubjectDAOImpl();
    private List<Subject> subjectsList = new ArrayList<>();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter(LAST_NAME);
        String firstName = request.getParameter(FIRST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String hashedPassword = Security.toHashPassword(password);
        String teacherType = request.getParameter(TEACHER_TYPE);
        TeacherType type = teacherTypeLocaleDAO.getTeacherTypeByLocaleTypeName(teacherType);
        String[] subjects = request.getParameterValues(SUBJECTS);
        for (String subjectName : subjects) {
            Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
            subjectsList.add(subject);
        }
        Boolean admin = Boolean.parseBoolean(request.getParameter(ADMIN));

        Teacher newTeacher = new Teacher(lastName, firstName, middleName, login, hashedPassword, subjectsList, type, admin);
        teacherDAO.addTeacher(newTeacher);

        Teacher teacher=teacherDAO.getTeacherByLoginAndPassword(login, hashedPassword);
        for (Subject subject : subjectsList) {
            teacherSubjectDAO.addTeacherSubject(teacher, subject);
        }

        HttpSession session = request.getSession();
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale = Locale.getDefault();
        }

        List<Teacher> teacherList = teacherDAO.getAllTeachers();
        List<Subject> subjectLocaleList = teacherSubjectDAO.getLocaleSubjectsOfaTeacher(teacher, locale);

        session.setAttribute(NEW_TEACHER, teacher);
        session.setAttribute(TEACHER_LIST, teacherList);
        session.setAttribute(SUBJECT_LOCALE_LIST, subjectLocaleList);

        request.getRequestDispatcher(TEACHER_LIST_JSP).forward(request, response);


    }


}
