package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static constants.JSPPagesNameConstants.INDEX_JSP;
import static constants.ParameterAndAttributeNameConstants.*;


public class StudentExitSystemAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute(STUDENT);
        }
        request.getRequestDispatcher(INDEX_JSP).forward(request,response);
    }
}

