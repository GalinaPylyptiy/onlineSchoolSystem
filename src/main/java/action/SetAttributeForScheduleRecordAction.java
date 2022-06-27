package action;

import dao.*;
import daoImpl.*;
import entity.Level;
import entity.Locale;
import entity.Subject;
import entity.Teacher;
import util.DayOfWeekConverter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class SetAttributeForScheduleRecordAction implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private String shortLocaleName = "ru_RU";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale = Locale.getDefault();
        }
        if(locale.getShortName().equals(shortLocaleName)) {
            String[] daysOfWeek = DayOfWeekConverter.getRussianDaysOfWeek();
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
