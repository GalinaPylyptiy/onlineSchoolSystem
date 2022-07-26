package action;

import dao.LevelDAO;
import dao.impl.LevelDAOImpl;
import entity.Level;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SetAttributeForStudentRegisterAction implements Action {

    private LevelDAO levelDAO = new LevelDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Level> levelList = levelDAO.getAllLevels();
        request.setAttribute(LEVEL_LIST, levelList);
        request.getRequestDispatcher(STUDENT_REGISTER_JSP).forward(request, response);
    }
}
