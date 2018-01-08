package controllers;

import annotation.Inject;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmEmailController extends InjectionServlet {

    private String TOKEN = "token";
    public static final String USER_PAGE = "user.jsp";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao.confirmEmail(req.getParameter(TOKEN));
        resp.sendRedirect(USER_PAGE);
    }
}
