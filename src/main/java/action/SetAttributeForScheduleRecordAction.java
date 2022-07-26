package action;

import dao.*;
import dao.impl.*;
import entity.Level;
import entity.Locale;
import entity.Subject;
import entity.Teacher;
import entity.DayOfWeek;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class SetAttributeForScheduleRecordAction implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private final String SHORT_LOCALE_NAME = "ru-RU";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale = Locale.getDefault();
        }
        if(locale.getShortName().equals(SHORT_LOCALE_NAME)) {
            String[] daysOfWeek = DayOfWeek.russianValues();
            session.setAttribute(DAYS_OF_WEEK_LIST, daysOfWeek);
        }
        else {
            DayOfWeek[] daysOfWeek = DayOfWeek.values();
            session.setAttribute(DAYS_OF_WEEK_LIST, daysOfWeek);
        }
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        List<Level> levels = levelDAO.getAllLevels();
        List<Subject> subjects = subjectLocaleDAO.getSubjectLocaleNameList(locale);
        session.setAttribute(LEVEL_LIST, levels);
        session.setAttribute(SUBJECT_LIST, subjects);
        session.setAttribute(TEACHER_LIST, teachers);
        request.getRequestDispatcher(SCHEDULE_JSP).forward(request, response);
    }
}
