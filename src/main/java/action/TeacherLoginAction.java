package action;

import constants.ErrorConstants;
import dao.TeacherDAO;
import entity.Teacher;
import dao.impl.TeacherDAOImpl;
import util.AccessChecker;
import util.Security;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TeacherLoginAction implements Action {

    private TeacherDAO teacherDAO = new TeacherDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String hashedPassword = Security.toHashPassword(password);
        if (AccessChecker.isTeacherRegistered(login,hashedPassword)){
            Teacher teacher = teacherDAO.getTeacherByLoginAndPassword(login,hashedPassword);
            HttpSession session = request.getSession(true);
            session.setAttribute(TEACHER, teacher);
            request.getRequestDispatcher(TEACHER_JSP).forward(request, response);
        } else {
           request.setAttribute(ErrorConstants.LOGIN_ERROR, LOGIN_ERROR_MSG);
           request.getRequestDispatcher(LOGIN_ERROR_JSP).forward(request, response);

        }
    }

}
