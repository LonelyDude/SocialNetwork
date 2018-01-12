<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Message, entity.User, java.util.*" %>
<%
List<Message> messages = (List<Message>) request.getAttribute("messages");
User user = (User) session.getAttribute("user");
User friend = (User) request.getAttribute("friend");
%>
<html>
<head>
    <title>Message</title>
</head>
<body>
<%
for(Message message : messages){
%>
<table border="1">
   <tr>
    <td>
        <%=message.getDate()%>
    </td>
        <%
        if(message.getFrom() == friend.getId()){
        %>
            <td>
                <%=friend%>
            </td>
        <%
        }else{
        %>
            <td>
                <%=user%>
            </td>
         <%
         }
         %>
    <td>
        <%=message.getContent()%>
    </td>
   </tr>
</table>
<%
}
%>
        <br>
        <form action= <%=request.getContextPath() + "/send?to=" + request.getAttribute("to")%> method="post">
        <label for="email-field">Message:</label>
        <input type="text" name="content"/>
        <br/>
        <input type="submit" value="Response"/>
        </form>
</body>
</html>