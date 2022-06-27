package action;
import dao.LocaleDAO;
import dao.SubjectLocaleDAO;
import daoImpl.LocaleDAOImpl;
import daoImpl.SubjectLocaleDAOImpl;
import entity.Level;
import entity.Locale;
import entity.Student;
import entity.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class GetSubjectListAttributeForStudentGrade implements Action {

    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       String localeShortName = (String) session.getAttribute(LOCALE);
       Locale locale = localeDAO.getLocaleByShortName(localeShortName);
       if(locale==null){
           locale=Locale.getDefault();
       }
       Student student = (Student)session.getAttribute(STUDENT);
       Level level = student.getLevel();
       List <Subject> subjectList = subjectLocaleDAO.getSubjectLocaleNameList(locale);
       session.setAttribute(STUDENT, student);
       session.setAttribute(LEVEL, level);
       session.setAttribute(SUBJECT_LIST, subjectList);
       request.getRequestDispatcher(GET_STUDENTS_GRADES_JSP).forward(request, response);
    }
}
