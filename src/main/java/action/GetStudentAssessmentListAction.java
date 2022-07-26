package action;
import dao.LevelDAO;
import dao.StudentAssessmentDAO;
import dao.SubjectLocaleDAO;
import dao.TeacherDAO;
import dao.impl.LevelDAOImpl;
import dao.impl.StudentAssessmentDAOImpl;
import dao.impl.SubjectLocaleDAOImpl;
import dao.impl.TeacherDAOImpl;
import entity.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class GetStudentAssessmentListAction implements Action {

    private TeacherDAO teacherDAO = new TeacherDAOImpl();
    private SubjectLocaleDAO subjectLocaleDAO = new SubjectLocaleDAOImpl();
    private LevelDAO levelDAO = new LevelDAOImpl();
    private StudentAssessmentDAO studentAssessmentDAO = new StudentAssessmentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID));
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        String subjectName = request.getParameter(SUBJECT);
        Subject subject = subjectLocaleDAO.getSubjectByLocaleName(subjectName);
        String levelName = request.getParameter(LEVEL);
        Level level = levelDAO.getLevelByLevelName(levelName);
        CurriculumRecord curriculumRecord = new CurriculumRecord();
        curriculumRecord.setSubject(subject);
        curriculumRecord.setLevel(level);
        List<StudentAssessment> assessmentList = studentAssessmentDAO.getAssessmentByLevelAndSubject(curriculumRecord);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(LEVEL, level);
        session.setAttribute(SUBJECT,subject);
        session.setAttribute(ASSESSMENT_LIST, assessmentList);
        request.getRequestDispatcher(ASSESSMENT_LIST_JSP).forward(request, response);
    }
}
