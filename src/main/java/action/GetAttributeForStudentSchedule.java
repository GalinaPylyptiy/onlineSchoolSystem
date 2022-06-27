package action;

import dao.LevelDAO;
import dao.ScheduleRecordDao;
import daoImpl.LevelDAOImpl;
import daoImpl.ScheduleRecordDAOImpl;
import entity.Level;
import entity.ScheduleRecord;
import entity.Student;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAttributeForStudentSchedule implements Action {

    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();
    private LevelDAO levelDAO = new LevelDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute(STUDENT);
        String levelName = student.getLevel().getName();
        Level level = levelDAO.getLevelByLevelName(levelName);
        List<ScheduleRecord> scheduleRecordList = scheduleRecordDao.getScheduleOfaStudent(level);
        session.setAttribute(STUDENT_SCHEDULE_LIST, scheduleRecordList);
        request.getRequestDispatcher(STUDENT_SCHEDULE_JSP).forward(request, response);


    }
}
