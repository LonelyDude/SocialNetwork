package controllers;

import annotation.Inject;
import dao.MessageDao;
import entity.Message;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class SendMessageController extends InjectionServlet {

    public static final String MAIN_MENU_PAGE = "login.jsp";

    @Inject("messageDao")
    private MessageDao messageDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        Message message = new Message();
        message.setContent(req.getParameter("content"));
        message.setFrom(user.getId());
        message.setTo(Integer.valueOf(req.getParameter("to")));
        message.setDate(LocalDate.now());
        messageDao.sendMessage(message);
        resp.sendRedirect(MAIN_MENU_PAGE);
    }
}
