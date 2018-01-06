package controllers;

import annotation.Inject;
import dao.UserDao;
import entity.User;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends InjectionServlet {

    public static final String MAIN_MENU_PAGE = "login.jsp";
    public static final String USER_PAGE = "user.jsp";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncСontext = req.startAsync();
        asyncСontext.start(()->{
            try{
                User user = userDao.login(req.getParameter(EMAIL), req.getParameter(PASSWORD));

                if(user == null) req.getRequestDispatcher(MAIN_MENU_PAGE).forward(req, resp);

                req.setAttribute("user", user);
                req.getRequestDispatcher(USER_PAGE).forward(req, resp);

            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                asyncСontext.complete();
            }
        });

    }
}
