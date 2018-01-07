package filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends HttpFilter {
    public static final String MAIN_MENU_PAGE = "login.jsp";
    public static final String USER_PAGE = "user.jsp";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getSession().getAttribute("user") == null){
            res.sendRedirect(MAIN_MENU_PAGE);
            return;
        }

        res.sendRedirect(USER_PAGE);
    }
}
