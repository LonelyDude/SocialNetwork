<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User, entity.Message, java.util.*" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
<%
List<User> users = (List<User>) request.getAttribute("users");
for(User user : users){
%>
<a href = <%=request.getContextPath() + "/message?messageId=" + user.getId()%> >Messages from:<%=user%><a/>
<%
}
%>
</body>
</html>