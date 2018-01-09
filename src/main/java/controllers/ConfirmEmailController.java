package controllers;

import annotation.Inject;
import dao.UserDao;
import dbc.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ConfirmEmailController extends InjectionServlet {

    private String TOKEN = "token";
    public static final String USER_PAGE = "user.jsp";

    @Inject("userDao")
    private UserDao userDao;

    @Inject("connectionManager")
    private ConnectionManager connectionManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Callable<Void> call = ()->{
            userDao.confirmEmail(req.getParameter(TOKEN));
            return null;
        };
        connectionManager.doInTransaction(call);
        resp.sendRedirect(USER_PAGE);
    }
}
