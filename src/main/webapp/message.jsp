<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Message, java.util.*" %>
<%Message message = (Message) request.getAttribute("message");%>
<html>
<head>
    <title>Message</title>
</head>
<body>
        <br>
        <form action="${pageContext.request.contextPath}/send?to=${message.getFrom()}" method="post">
        <label for="email-field">Message:</label>
        <input type="text" name="content"/>
        <br/>
        <input type="submit" value="Response"/>
        </form>
</body>
</html>