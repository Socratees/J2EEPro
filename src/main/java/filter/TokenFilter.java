package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class TokenFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
//        String username = request.getParameter("username");
//        System.out.println(username);
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }


}
