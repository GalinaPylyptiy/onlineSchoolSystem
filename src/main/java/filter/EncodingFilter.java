package filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private  String encoding = "UTF-8";
    private String contentType = "text/html;charset=UTF-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
           request.setCharacterEncoding(encoding);
           response.setContentType(contentType);
           filterChain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    String encodingParam = filterConfig.getInitParameter(encoding);
    if(encodingParam!=null){
        encoding = encodingParam;
    }
    }

    @Override
    public void destroy() {

    }
}
