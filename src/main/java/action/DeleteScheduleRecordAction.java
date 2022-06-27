package action;

import dao.ScheduleRecordDao;
import daoImpl.ScheduleRecordDAOImpl;
import entity.Level;
import entity.ScheduleRecord;
import entity.Subject;
import entity.Teacher;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteScheduleRecordAction implements Action {

    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long scheduleRecordId = Long.parseLong(request.getParameter(SCHEDULE_RECORD_ID));
        scheduleRecordDao.deleteRecord(scheduleRecordId);
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute(TEACHER);
        Subject subject = (Subject) session.getAttribute(SUBJECT);
        Level level = (Level) session.getAttribute(LEVEL);
        List<ScheduleRecord> scheduleRecordList = scheduleRecordDao.getAllRecords();
        session.setAttribute(TEACHER,teacher);
        session.setAttribute(LEVEL, level);
        session.setAttribute(SUBJECT, subject);
        session.setAttribute(SCHEDULE_RECORD_LIST, scheduleRecordList);
        request.getRequestDispatcher(SCHEDULE_RECORD_LIST_JSP).forward(request,response);


    }
}
