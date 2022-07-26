package action;

import constants.ParameterAndAttributeNameConstants;
import dao.CurriculumRecordDAO;
import dao.StudentAssessmentDAO;
import dao.StudentDAO;
import dao.impl.CurriculumRecordDAOImpl;
import dao.impl.StudentAssessmentDAOImpl;
import dao.impl.StudentDAOImpl;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private Logger logger = LogManager.getLogger(this.getClass().getName());
    private StudentDAO studentDAO = new StudentDAOImpl();
    private CurriculumRecordDAO curriculumRecordDAO = new CurriculumRecordDAOImpl();
    private StudentAssessmentDAO studentAssessmentDAO = new StudentAssessmentDAOImpl();
    private int grade;
    private final String assessmentError = "Please, choose grade!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Teacher teacher = (Teacher)session.getAttribute(TEACHER);
    Subject subject = (Subject)session.getAttribute(SUBJECT);
    Level level =(Level) session.getAttribute(LEVEL);
    Long studentId = Long.parseLong(request.getParameter(STUDENT_ID));
    long recordId = Long.parseLong(request.getParameter(RECORD_ID));
    String gradeString = request.getParameter(GRADE);
    try {
        grade = Integer.parseInt(gradeString);
    } catch (NumberFormatException ex){
        logger.error(ex.getCause());
        request.setAttribute(ASSESSMENT_ERROR, assessmentError);
        request.getRequestDispatcher(ASSESSMENT_ERROR_JSP).forward(request, response);
        return;
    }
    Student student = studentDAO.getStudentById(studentId);
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
