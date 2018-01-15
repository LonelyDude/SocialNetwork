package filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends HttpFilter {
    private static final String MAIN_MENU = "login";
    private static final String SIGN_UP = "signUp";
    private static final String JSP = ".jsp";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getSession().getAttribute("user") == null){
            String path = req.getRequestURI();
            if(path.contains(SIGN_UP)){
                if(path.contains(JSP)){
                    req.getRequestDispatcher(SIGN_UP + JSP).forward(req, res);
                    return;
                }
                req.getRequestDispatcher(SIGN_UP).forward(req, res);
            }
            else{
                if(path.contains(JSP)){
                    req.getRequestDispatcher(MAIN_MENU + JSP).forward(req, res);
                    return;
                }
                req.getRequestDispatcher(MAIN_MENU).forward(req, res);
            }
            return;
        }
        chain.doFilter(req, res);
    }
}
