package controllers;

import annotation.Inject;
import dao.MessageDao;
import entity.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MessageController extends InjectionServlet{

    private static final String MESSAGE_PAGE = "message.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messages = (LinkedList<Message>)req.getAttribute("messages");
        for(Message message : messages){
            if(message.getId() == Integer.valueOf(req.getParameter("messageId"))){
                req.setAttribute("message", message);
                break;
            }
        }
        req.getRequestDispatcher(MESSAGE_PAGE).forward(req, resp);
    }
}
