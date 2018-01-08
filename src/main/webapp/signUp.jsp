<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="\c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
        <c:if request.getAttribute("error") != null>
            <p><%=request.getAttribute("error")%></p>
        </c:if>
        <form action="${pageContext.request.contextPath}/signUp" method="post">
            <label for="email-field">Email:</label>
            <input type="text" name="email" id="login-field"/>
            <br/>
            <label for="password-field">Password:</label>
            <input type="text" name="password" id="password-field"/>
            <br/>
            <label for="name-field">Name:</label>
            <input type="text" name="name" id="password-field"/>
            <br/>
            <label for="lastName-field">Last name:</label>
            <input type="text" name="lastName" id="password-field"/>
            <br/>
            <label for="sex-field">M</label>
            <input name="sex" type="radio" value="M"/>
            <label for="sex-field">F</label>
            <hr><input name="sex" type="radio" value="F"/>
            <br/>
            <label for="dateOfBirth-field">Date of birth:</label>
            <input type="date" name="calendar"/>
            <br/>
            <input type="submit" value="Sign up"/>
        </form>
</body>
</html>