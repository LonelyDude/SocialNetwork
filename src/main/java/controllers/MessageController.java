package controllers;

import annotation.Inject;
import dao.MessageDao;
import dao.UserDao;
import entity.Message;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MessageController extends InjectionServlet{

    private static final String MESSAGE_PAGE = "message.jsp";

    @Inject("messageDao")
    private MessageDao messageDao;

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int fromId = Integer.valueOf(req.getParameter("messageId"));
        User friend = userDao.getUser(fromId);

        List<Message> messages = messageDao.getMessages(user, fromId);
        Collections.sort(messages, (a, b)->{
            return a.getDate().compareTo(b.getDate());
        });
        req.setAttribute("messages", messages);
        req.setAttribute("to", fromId);
        req.setAttribute("friend", friend);
        req.getRequestDispatcher(MESSAGE_PAGE).forward(req, resp);
    }
}
