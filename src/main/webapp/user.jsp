<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%=import entity.User;%>
<%=User user = (User) req.getSession().getAttribute("user");%>
<html>
<head>
    <title>Пользователь</title>
</head>
<body>
<h1>Name:<%=user.getName()%></h1>
</body>
</html>