package action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorAction implements Action {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
       LOGGER.error("Resource " + request.getRequestURI() + " is not found");
    }
}
