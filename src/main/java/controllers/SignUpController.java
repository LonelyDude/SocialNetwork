package controllers;

import annotation.Inject;
import dao.UserDao;
import dbc.ConnectionManager;
import entity.User;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignUpController extends InjectionServlet {

    private String SIGN_UP_PAGE = "signUp.jsp";
    private String USER_PAGE = "user.jsp";
    private String EMAIL = "email";
    private String PASSWORD = "password";
    private String NAME = "name";
    private String LAST_NAME = "lastName";
    private String SEX = "sex";
    private String DATE = "date";

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.getAsyncContext();
        asyncContext.start(()->{
            try{
                if(! userDao.checkEmail(req.getParameter(EMAIL))){
                    req.setAttribute("error", "Email already exists.");
                    resp.sendRedirect(SIGN_UP_PAGE);
                    return;
                }

                User user = makeUser(req);

                if(! userDao.addUser(user)){
                    req.setAttribute("error", "Try later.");
                    resp.sendRedirect(SIGN_UP_PAGE);
                    return;
                }else{
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect(USER_PAGE);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                asyncContext.complete();
            }
        });
    }

    private User makeUser(HttpServletRequest req){
        User user = new User();
        user.setName(req.getParameter(NAME));
        user.setLastName(req.getParameter(LAST_NAME));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        user.setBirth(LocalDate.parse(req.getParameter(DATE), formatter));

        user.setSex(req.getParameter(SEX).charAt(0));
        return user;
    }

    private void sendConfirmingRef(){

    }
}
