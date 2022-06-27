package action;

import dao.*;
import entity.*;
import daoImpl.*;
import util.DayOfWeekConverter;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class AddScheduleRecordAction implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private String shortNameLocale = "ru_RU";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DayOfWeek dayOfWeek = null;
        String localeShortName =(String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(localeShortName);
        if(locale==null){
            locale = Locale.getDefault();
        }
        String weekDay = request.getParameter(DAY_OF_WEEK);
        String time = request.getParameter(TIME);
        LocalTime localTime = LocalTime.parse(time);
        String levelName = request.getParameter(LEVEL);
        String subjectName = request.getParameter(SUBJECT);
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID));
        if(locale.getShortName().equals(shortNameLocale)){
            dayOfWeek = DayOfWeekConverter.convertDayOfWeek(weekDay);
        }
        else {
            dayOfWeek = DayOfWeek.valueOf(weekDay);
        }
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        Level level = levelDAO.getLevelByLevelName(levelName);
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        ScheduleRecord scheduleRecord = new ScheduleRecord(dayOfWeek, localTime, level, subject, teacher);
        scheduleRecordDao.addNewRecord(scheduleRecord);
        List<ScheduleRecord> scheduleRecordList = scheduleRecordDao.getAllRecords();
        session.setAttribute(LEVEL, level);
        session.setAttribute(SUBJECT, subject);
        session.setAttribute(SCHEDULE_RECORD,scheduleRecord);
        session.setAttribute(SCHEDULE_RECORD_LIST, scheduleRecordList);
        request.getRequestDispatcher(SCHEDULE_RECORD_LIST_JSP).forward(request,response);
    }

}
