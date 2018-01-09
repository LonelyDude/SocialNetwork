<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="login-field">Login:</label>
            <input type="text" name="login" id="login-field">
            <br/>
            <label for="password-field">Password:</label>
            <input type="text" name="password" id="password-field">
            <br/>
            <input type="submit" value="Login" />
            <br/>
            <a href="${pageContext.request.contextPath}/signUp.jsp">Sign up<a/>
        </form>
</body>
</html>