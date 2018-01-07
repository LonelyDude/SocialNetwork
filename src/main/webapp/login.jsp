<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix=»c» uri=»http://java.sun.com/jsp/jstl/core» %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="login-field">Ваш логин</label>
            <input type="text" name="login" id="login-field">
            <br><br>
            <label for="password-field">Ваш пароль</label>
            <input type="text" name="password" id="password-field" value="">
            <input type="submit" value="Войти" />
        </form>
</body>
</html>