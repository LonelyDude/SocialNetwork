package controllers;

import annotation.Inject;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends InjectionServlet {

    private static final String MAIN_MENU_PAGE = "login.jsp";
    private static final String USER_PAGE = "user.jsp";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = userDao.login(req.getParameter(LOGIN), req.getParameter(PASSWORD));

            if(user == null){
                resp.sendRedirect(MAIN_MENU_PAGE);
                return;
            }

            req.getSession().setAttribute("user", user);
            resp.sendRedirect(USER_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
