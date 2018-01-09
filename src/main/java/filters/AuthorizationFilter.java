package filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends HttpFilter {
    public static final String MAIN_MENU_PAGE = "login.jsp";
    public static final String SIGN_UP_PAGE = "signUp.jsp";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getSession().getAttribute("user") == null){
            String path = req.getRequestURI();
            if(path.contains(SIGN_UP_PAGE)){
                req.getRequestDispatcher(SIGN_UP_PAGE).forward(req, res);
            }
            else{
                req.getRequestDispatcher(MAIN_MENU_PAGE).forward(req, res);
            }
            return;
        }
        chain.doFilter(req, res);
    }
}
