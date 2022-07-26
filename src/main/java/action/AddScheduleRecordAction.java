package action;

import dao.*;
import entity.*;
import dao.impl.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import static constants.ErrorConstants.*;

public class AddScheduleRecordAction implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();
    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private final String SHORT_NAME_LOCALE = "ru-RU";

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
        if(locale.getShortName().equals(SHORT_NAME_LOCALE)){
            dayOfWeek = DayOfWeek.getDayOfWeek(weekDay);
        }
        else {
            dayOfWeek = DayOfWeek.valueOf(weekDay);
        }
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        Level level = levelDAO.getLevelByLevelName(levelName);
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        ScheduleRecord newScheduleRecord = new ScheduleRecord(dayOfWeek, localTime, level, subject, teacher);
        List<ScheduleRecord> scheduleRecordList = scheduleRecordDao.getAllRecords();
        for(ScheduleRecord record : scheduleRecordList){
          if(record.equals(newScheduleRecord)){
              request.setAttribute(SCHEDULE_RECORD_ERROR, SCHEDULE_RECORD_ERROR_MSG);
              request.getRequestDispatcher(SCHEDULE_RECORD_ERROR_JSP).forward(request,response);
              return;
          }
        }
        scheduleRecordDao.addNewRecord(newScheduleRecord);
        List<ScheduleRecord> newScheduleRecordList = scheduleRecordDao.getAllRecords();
        session.setAttribute(LEVEL, level);
        session.setAttribute(SUBJECT, subject);
        session.setAttribute(SCHEDULE_RECORD,newScheduleRecord);
        session.setAttribute(SCHEDULE_RECORD_LIST, newScheduleRecordList);
        request.getRequestDispatcher(SCHEDULE_RECORD_LIST_JSP).forward(request,response);
    }

}
