package action;

import dao.LocaleDAO;
import dao.ScheduleRecordDao;
import dao.TeacherSubjectListDAO;
import dao.impl.LocaleDAOImpl;
import dao.impl.ScheduleRecordDAOImpl;
import dao.impl.TeacherSubjectDAOImpl;
import entity.Level;
import entity.Locale;
import entity.Subject;
import entity.Teacher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class SetAttributeForCurriculumRecordAction implements Action {

    private TeacherSubjectListDAO teacherSubjectListDAO = new TeacherSubjectDAOImpl();
    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String shortName = (String) session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(shortName);
        if(locale==null){
            locale=Locale.getDefault();
        }
        Teacher teacher = (Teacher) session.getAttribute(TEACHER);
        session.setAttribute(TEACHER, teacher);
        List<Subject> subjectList = teacherSubjectListDAO.getLocaleSubjectsOfaTeacher(teacher, locale);
        session.setAttribute(SUBJECT_LIST, subjectList);
        List<Level> levelList = scheduleRecordDao.getLevelsOfTeacher(teacher);
        session.setAttribute(LEVEL_LIST, levelList);
        request.getRequestDispatcher(ADD_CURRICULUM_JSP).forward(request, response);
    }
}
