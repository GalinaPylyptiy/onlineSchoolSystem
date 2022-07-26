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
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SetAttributeForAssessmentRecordAction implements Action {

    private TeacherSubjectListDAO teacherSubjectListDAO = new TeacherSubjectDAOImpl();
    private ScheduleRecordDao scheduleRecordDao = new ScheduleRecordDAOImpl();
    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String shortName=(String)session.getAttribute(LOCALE);
        Locale locale = localeDAO.getLocaleByShortName(shortName);
        if (locale == null){
            locale=Locale.getDefault();
        }
        Teacher teacher = (Teacher)session.getAttribute(TEACHER);
        List<Subject> subjectList = teacherSubjectListDAO.getLocaleSubjectsOfaTeacher(teacher,locale);
        List<Level> levelList = scheduleRecordDao.getLevelsOfTeacher(teacher);
        session.setAttribute(SUBJECT_LIST, subjectList);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(LEVEL_LIST, levelList);
        request.getRequestDispatcher(INFO_FOR_ASSESSMENT_JSP).forward(request, response);

    }
}
