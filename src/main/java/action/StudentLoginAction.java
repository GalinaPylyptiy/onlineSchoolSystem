package action;
import constants.ErrorConstants;
import dao.StudentDAO;
import entity.Student;
import dao.impl.StudentDAOImpl;
import util.AccessChecker;
import util.Security;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentLoginAction implements Action {

    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    String login = request.getParameter(LOGIN);
    String password = request.getParameter(PASSWORD);
    String hashedPassword = Security.toHashPassword(password);
        if(AccessChecker.isStudentRegistered(login, hashedPassword)){
     Student student = studentDAO.getStudentByLoginAndPassword(login, hashedPassword);
     session.setAttribute(STUDENT, student);
     request.getRequestDispatcher(STUDENT_JSP).forward(request, response);
        }
   else {
            request.setAttribute(ErrorConstants.LOGIN_ERROR, LOGIN_ERROR_MSG);
             request.getRequestDispatcher(LOGIN_ERROR_JSP).forward(request, response);
    }
    }
}
