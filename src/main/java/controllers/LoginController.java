package controllers;

import annotation.Inject;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends InjectionServlet {

    public static final String MAIN_MENU_PAGE = "menu.jsp";
    public static final String USER_PAGE = "user.jsp";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

//    @Inject("transactionManager")
//    private TransactionManager transactionManager;

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.login(req.getParameter(EMAIL), req.getParameter(PASSWORD));
        if(user == null){
            req.getRequestDispatcher(MAIN_MENU_PAGE).forward(req, resp);
            return;
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher(USER_PAGE).forward(req, resp);
    }
}
