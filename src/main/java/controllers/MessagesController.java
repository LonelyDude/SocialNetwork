package controllers;

import annotation.Inject;
import dao.MessageDao;
import entity.Message;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MessagesController extends InjectionServlet {

    private static final String MESSAGES_PAGE = "messages.jsp";

    @Inject("messageDao")
    private MessageDao messageDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> list = messageDao.getMessages((User) req.getSession().getAttribute("user"));
        Collections.sort(list, (a, b)->{
            return a.getDate().compareTo(b.getDate());
        });
        req.setAttribute("messages", list);
        req.getRequestDispatcher(MESSAGES_PAGE).forward(req, resp);
    }
}
