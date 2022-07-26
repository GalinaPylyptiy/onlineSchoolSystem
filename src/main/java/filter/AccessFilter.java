package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static constants.JSPPagesNameConstants.*;
import static constants.ParameterAndAttributeNameConstants.*;

public class AccessFilter implements Filter {
    private final String [] ACCESSIBLE_PAGES = {"/welcome.jsp","/chooseLocale.jsp","/index.jsp","/teacherLogin.jsp","/studentLogin.jsp"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();
        for (String accessiblePage : ACCESSIBLE_PAGES) {
            if (requestUri.endsWith(accessiblePage)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        HttpSession session = request.getSession();
        if(session.getAttribute(TEACHER)==null && session.getAttribute(STUDENT)==null){
            request.getRequestDispatcher(INDEX_JSP).forward(request, response);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
            }
    @Override
    public void destroy() {

    }
}
