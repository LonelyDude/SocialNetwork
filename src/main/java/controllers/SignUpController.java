package controllers;

import annotation.Inject;
import dao.UserDao;
import entity.User;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpController extends InjectionServlet {

    private String SIGN_UP_PAGE = "signUp.jsp";
    private String USER_PAGE = "user.jsp";
    private String EMAIL = "email";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.getAsyncContext();
        asyncContext.start(()->{
            try{
                User user = new User();

                if(! userDao.addUser(user)){
                    req.setAttribute("error", "Email already exists.");
                    resp.sendRedirect(SIGN_UP_PAGE);
                    return;
                }

                req.getSession().setAttribute("user", user);
                resp.sendRedirect(USER_PAGE);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                asyncContext.complete();
            }
        });
    }
}
