<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%User user = (User) request.getSession().getAttribute("user");%>
<html>
<head>
    <title>User</title>
</head>
<body>
       <cite>Name:<%=user.getName()%></cite>
       <br>
       <cite>Name:<%=user.getLastName()%></cite>
       <br>
       <a href="${pageContext.request.contextPath}/messages">Messages<a/>
       <br>
       <form action="${pageContext.request.contextPath}/send" method="post">
       <label for="email-field">Message:</label>
       <input type="text" name="content"/>
       <br/>
       <input type="text" name="to"/>
       <br/>
       <input type="submit" value="Response"/>
       </form>
</body>
</html>