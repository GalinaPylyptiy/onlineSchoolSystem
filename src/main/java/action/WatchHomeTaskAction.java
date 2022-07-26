package action;

import dao.CurriculumRecordDAO;
import dao.LocaleDAO;
import dao.SubjectLocaleDAO;
import dao.impl.CurriculumRecordDAOImpl;
import dao.impl.LocaleDAOImpl;
import dao.impl.SubjectLocaleDAOImpl;
import entity.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class WatchHomeTaskAction implements Action {

    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();
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
        Level level = (Level) session.getAttribute(LEVEL);
        String subjectName = request.getParameter(SUBJECT);

        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);

        List<CurriculumRecord> recordList =curriculumRecordDAO.getCurriculumRecordsByLevelAndSubject(level, subject);

        Subject subjectLocale = subjectLocaleDAO.getSubjectLocaleBySubjectAndLocale(subject, locale);

        session.setAttribute(SUBJECT, subjectLocale);
        session.setAttribute(RECORD_LIST, recordList);

        request.getRequestDispatcher(SHOW_HOME_TASK_TO_STUDENT_JSP).forward(request, response);

    }
}
