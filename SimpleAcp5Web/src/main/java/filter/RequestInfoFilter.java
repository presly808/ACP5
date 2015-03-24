package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestInfoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("Stat FILTER");
        System.out.printf("method %s\nquery %s\naddress %s",
                req.getMethod(), req.getServletPath(), req.getRemoteAddr());

        chain.doFilter(req, resp); // go forward through chain
    }

    @Override
    public void destroy() {

    }
}
