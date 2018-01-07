package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends InjectionServlet {
    public static final String MAIN_MENU_PAGE = "login.jsp";
    public static final String USER_PAGE = "user.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null){
            req.getRequestDispatcher(MAIN_MENU_PAGE).forward(req, resp);
            return;
        }

        req.getRequestDispatcher(USER_PAGE).forward(req, resp);
    }
}
