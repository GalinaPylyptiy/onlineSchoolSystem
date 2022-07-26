package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static constants.JSPPagesNameConstants.*;
import static constants.ParameterAndAttributeNameConstants.*;

public class TeacherExitSystemAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute(TEACHER);
        }
        request.getRequestDispatcher(INDEX_JSP).forward(request,response);
    }
}
