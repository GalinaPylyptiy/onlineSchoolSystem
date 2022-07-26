package action;

import dao.StudentAssessmentDAO;
import dao.impl.StudentAssessmentDAOImpl;
import entity.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteAssessmentRecordAction implements Action {

    private StudentAssessmentDAO studentAssessmentDAO = new StudentAssessmentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long assessmentRecordId = Long.parseLong(request.getParameter(ASSESSMENT_RECORD_ID));
        studentAssessmentDAO.deleteAssessmentRecord(assessmentRecordId);
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute(TEACHER);
        Subject subject = (Subject)session.getAttribute(SUBJECT);
        Level level =(Level) session.getAttribute(LEVEL);
        CurriculumRecord curriculumRecord = new CurriculumRecord();
        curriculumRecord.setLevel(level);
        curriculumRecord.setSubject(subject);
        List<StudentAssessment> assessmentList = studentAssessmentDAO.getAssessmentByLevelAndSubject(curriculumRecord);
        session.setAttribute(TEACHER, teacher);
        session.setAttribute(ASSESSMENT_LIST,assessmentList);
        request.getRequestDispatcher(ASSESSMENT_LIST_JSP).forward(request, response);

    }
}
