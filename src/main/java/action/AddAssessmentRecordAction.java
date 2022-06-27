package action;

import constants.ParameterAndAttributeNameConstants;
import dao.CurriculumRecordDAO;
import dao.StudentAssessmentDAO;
import dao.StudentDAO;
import daoImpl.CurriculumRecordDAOImpl;
import daoImpl.StudentAssessmentDAOImpl;
import daoImpl.StudentDAOImpl;
import entity.*;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import static constants.ErrorConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddAssessmentRecordAction implements Action {

    private StudentDAO studentDAO = new StudentDAOImpl();
    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();
    private StudentAssessmentDAO studentAssessmentDAO = new StudentAssessmentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Teacher teacher = (Teacher)session.getAttribute(TEACHER);
    Subject subject = (Subject)session.getAttribute(SUBJECT);
    Long studentId = Long.parseLong(request.getParameter(STUDENT_ID));
    long recordId = Long.parseLong(request.getParameter(RECORD_ID));
    int grade = Integer.parseInt(request.getParameter(GRADE));
    Student student = studentDAO.getStudentById(studentId);
    Level level =(Level) session.getAttribute(LEVEL);
    CurriculumRecord record = curriculumRecordDAO.getRecordById(recordId);
    LocalDate assessmentDate = LocalDate.now();
    StudentAssessment newAssessment = new StudentAssessment(record, student, grade, assessmentDate);
    List<StudentAssessment> assessmentList = studentAssessmentDAO.getAssessmentByLevelAndSubject(record);
    for(StudentAssessment assessment:assessmentList){
        if(newAssessment.getCurriculumRecord().getId().equals(assessment.getCurriculumRecord().getId()) &&
                newAssessment.getStudent().getId().equals(assessment.getStudent().getId())){
            request.setAttribute(ASSESSMENT_ERROR, ASSESSMENT_ERROR_MSG);
            request.getRequestDispatcher(ASSESSMENT_ERROR_JSP).forward(request, response);
            return;
        }
    }
    studentAssessmentDAO.addAssessmentRecord(newAssessment);
    List<StudentAssessment> assessmentList2 = studentAssessmentDAO.getAssessmentByLevelAndSubject(record);
    session.setAttribute(TEACHER, teacher);
    session.setAttribute(SUBJECT, subject);
    session.setAttribute(LEVEL, level);
    session.setAttribute(ParameterAndAttributeNameConstants.ASSESSMENT_LIST,assessmentList2);
    request.getRequestDispatcher(ASSESSMENT_LIST_JSP).forward(request, response);

    }
}
