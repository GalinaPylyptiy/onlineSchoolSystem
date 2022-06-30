package action;
import dao.LocaleDAO;
import dao.StudentAssessmentDAO;
import dao.SubjectLocaleDAO;
import daoImpl.LocaleDAOImpl;
import daoImpl.StudentAssessmentDAOImpl;
import daoImpl.SubjectLocaleDAOImpl;
import entity.Locale;
import entity.Student;
import entity.StudentAssessment;
import entity.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class GetStudentsGradesAction implements Action{

    private StudentAssessmentDAO studentAssessmentDAO = new StudentAssessmentDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeShortName = (String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null) {
            locale = Locale.getDefault();
        }
        Student student = (Student) session.getAttribute(STUDENT);
        String subjectName = request.getParameter(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        String sinceDate = request.getParameter(SINCE_DATE);
        String untilDate = request.getParameter(UNTIL_DATE);
        LocalDate since = LocalDate.parse(sinceDate);
        LocalDate until = LocalDate.parse(untilDate);
        List<StudentAssessment> assessmentList = studentAssessmentDAO.getGradesOfStudentForTimePeriod(student, subject, since, until);
        Double averageGrade = studentAssessmentDAO.getAverageGradeForTimePeriod(student, subject, since, until);
        Subject subjectLocale = subjectLocaleDAO.getSubjectLocaleBySubjectAndLocale(subject,locale);
        session.setAttribute(STUDENT, student);
        session.setAttribute(SUBJECT, subjectLocale);
        session.setAttribute(ASSESSMENT_LIST, assessmentList);
        session. setAttribute(AVERAGE_GRADE, averageGrade);
        request.getRequestDispatcher(SHOW_GRADES_TO_STUDENT_JSP).forward(request, response);
    }
}
