package action;

import dao.ScheduleRecordDao;
import dao.impl.ScheduleRecordDAOImpl;
import entity.ScheduleRecord;
import entity.Teacher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class GetAttributeForTeacherSchedule implements Action {

    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute(TEACHER);
        List<ScheduleRecord> scheduleRecordList = scheduleRecordDao.getScheduleOfaTeacher(teacher);
        session.setAttribute(TEACHER_SCHEDULE, scheduleRecordList);
        request.getRequestDispatcher(TEACHER_SCHEDULE_JSP).forward(request, response);

    }
}
