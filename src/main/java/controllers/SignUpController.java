package controllers;

import annotation.Inject;
import dao.UserDao;
import dbc.ConnectionManager;
import entity.User;
import mail.MailManager;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class SignUpController extends InjectionServlet {

    private static final String SIGN_UP_PAGE = "signUp.jsp";
    private static final String USER_PAGE = "user.jsp";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String SEX = "sex";
    private static final String DATE = "birth";

    @Inject("userDao")
    private UserDao userDao;

    @Inject("connectionManager")
    private ConnectionManager connectionManager;

    @Inject("mailManager")
    private MailManager mailManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(! userDao.checkEmail(req.getParameter(EMAIL))){
            req.setAttribute("error", "Email already exists.");
            resp.sendRedirect(SIGN_UP_PAGE);
            return;
        }

        User user = makeUser(req);

        Callable<String> call = ()-> userDao.addUser(user, req.getParameter(EMAIL), req.getParameter(PASSWORD));
        String token = connectionManager.doInTransaction(call);

        if(token == null){
            req.setAttribute("error", "Try later.");
            resp.sendRedirect(SIGN_UP_PAGE);
        } else{
            mailManager.sendToken(req.getParameter(EMAIL), token);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(USER_PAGE);
        }
        //        final AsyncContext asyncContext = req.getAsyncContext();
//        asyncContext.start(()->{
//            try{
//                if(! userDao.checkEmail(req.getParameter(EMAIL))){
//                    req.setAttribute("error", "Email already exists.");
//                    resp.sendRedirect(SIGN_UP_PAGE);
//                    return;
//                }
//
//                User user = makeUser(req);
//
//                Callable<String> call = ()-> userDao.addUser(user, req.getParameter(EMAIL), req.getParameter(PASSWORD));
//                String token = connectionManager.doInTransaction(call);
//
//                if(token == null){
//                    req.setAttribute("error", "Try later.");
//                    resp.sendRedirect(SIGN_UP_PAGE);
//                } else{
//                    mailManager.sendToken(req.getParameter(EMAIL), token);
//                    req.getSession().setAttribute("user", user);
//                    resp.sendRedirect(USER_PAGE);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                asyncContext.complete();
//            }
//        });
    }

    private User makeUser(HttpServletRequest req){
        User user = new User();
        user.setName(req.getParameter(NAME));
        user.setLastName(req.getParameter(LAST_NAME));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirth(LocalDate.parse(req.getParameter(DATE), formatter));

        user.setSex(req.getParameter(SEX).charAt(0));
        return user;
    }
}
