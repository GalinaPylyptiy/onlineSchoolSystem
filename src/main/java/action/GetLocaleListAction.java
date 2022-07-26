package action;
import dao.LocaleDAO;
import dao.impl.LocaleDAOImpl;
import entity.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static constants.ParameterAndAttributeNameConstants.*;
import static constants.JSPPagesNameConstants.*;

public class GetLocaleListAction implements Action {

    private LocaleDAO localeDAO = new LocaleDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Locale> localeList=localeDAO.getAllLocales();
        session.setAttribute(LOCALE_LIST, localeList);
        request.getRequestDispatcher(INDEX_JSP).forward(request,response);

    }
}
