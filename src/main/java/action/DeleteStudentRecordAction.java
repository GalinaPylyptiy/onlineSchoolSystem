package action;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import entity.Student;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteStudentRecordAction implements Action {

    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Long studentId = Long.parseLong(request.getParameter(STUDENT_ID));
    studentDAO.deleteStudent(studentId);
        List<Student> studentList = studentDAO.getAllStudents();
        HttpSession session = request.getSession();
        session.setAttribute(STUDENT_LIST, studentList);
        request.getRequestDispatcher(STUDENT_LIST_JSP).forward(request, response);

    }
}
