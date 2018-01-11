package controllers;

import annotation.Inject;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends InjectionServlet {

    private static final String USER_PAGE = "user.jsp";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("user", userDao.getUser(id));
        req.getRequestDispatcher(USER_PAGE).forward(req, resp);
    }
}
