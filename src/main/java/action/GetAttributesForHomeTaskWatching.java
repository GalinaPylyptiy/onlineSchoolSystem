package action;

import dao.LevelDAO;
import dao.LocaleDAO;
import dao.SubjectLocaleDAO;
import dao.impl.LevelDAOImpl;
import dao.impl.LocaleDAOImpl;
import dao.impl.SubjectLocaleDAOImpl;
import entity.Level;
import entity.Locale;
import entity.Student;
import entity.Subject;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAttributesForHomeTaskWatching implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale=Locale.getDefault();
        }
        Student student = (Student) session.getAttribute(STUDENT);
        String levelName = student.getLevel().getName();
        Level level = levelDAO.getLevelByLevelName(levelName);
        List<Subject> subjectList = subjectLocaleDAO.getSubjectLocaleNameList(locale);
        session.setAttribute(STUDENT, student);
        session.setAttribute(LEVEL, level);
        session.setAttribute(SUBJECT_LIST, subjectList);
        request.getRequestDispatcher(STUDENT_HOME_TASK_JSP).forward(request,response);
    }
}
