<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User, entity.Message, java.util.*" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
           <c:forEach items="${messages}" var="message">
           <c:when test="${message}.getTo() == ${user}.getId()">
               <tr>
                   <br>
                     <a href="${pageContext.request.contextPath}/message?messageId=${message.getId()}">Message content<a/>
               </tr>
           </c:when>
           </c:forEach>
</body>
</html>