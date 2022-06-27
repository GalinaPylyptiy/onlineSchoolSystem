package controller;

import action.Action;
import action.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SchoolSystemOnlineController extends HttpServlet {

    private static final long serialVersionUID = -5388824852130262159L;
    private ActionFactory actionFactory = ActionFactory.getInstance();
    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
    String requestString = request.getRequestURI();
        Action action = actionFactory.getAction(requestString);
        try {
            action.execute(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
    }
}
