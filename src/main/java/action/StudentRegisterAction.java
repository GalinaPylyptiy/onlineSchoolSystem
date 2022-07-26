package action;

import dao.LevelDAO;
import dao.StudentDAO;
import entity.Level;
import entity.Student;
import dao.impl.LevelDAOImpl;
import dao.impl.StudentDAOImpl;
import util.Security;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class StudentRegisterAction implements Action{

    private LevelDAO levelDAO = new LevelDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter(LAST_NAME);
        String firstName = request.getParameter(FIRST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String hashedPassword = Security.toHashPassword(password);
        String levelName = request.getParameter(LEVEL);
        Level level = levelDAO.getLevelByLevelName(levelName);
        Student student = new Student(lastName, firstName, middleName, login, hashedPassword, level);
        studentDAO.addStudent(student);
        List<Student> studentList = studentDAO.getAllStudents();
        HttpSession session = request.getSession();
        session.setAttribute(STUDENT, student);
        session.setAttribute(STUDENT_LIST, studentList);
        request.getRequestDispatcher(STUDENT_LIST_JSP).forward(request,response);

    }
}
