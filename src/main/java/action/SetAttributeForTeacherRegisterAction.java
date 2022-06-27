package action;
import dao.*;
import daoImpl.*;
import entity.Locale;
import entity.Subject;
import entity.TeacherType;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SetAttributeForTeacherRegisterAction implements Action{

    private TeacherTypeLocaleDAO teacherTypeLocaleDAO = new TeacherTypeLocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale = Locale.getDefault();
        }
        List<TeacherType> typeList = teacherTypeLocaleDAO.getTeacherTypeLocaleList(locale);
        List<Subject> subjectList = subjectLocaleDAO.getSubjectLocaleNameList(locale);
        request.setAttribute(TEACHER_TYPE_LIST, typeList);
        request.setAttribute(SUBJECT_LIST, subjectList);
        request.getRequestDispatcher(TEACHER_REGISTER_JSP).forward(request, response);
    }
}
