<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Message, entity.User, java.util.*" %>
<%
List<Message> messages = (List<Message>) request.getAttribute("messages");
User user = (User) session.getAttribute("user");
User friend = (User) request.getAttribute("friend");
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Message</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/form.css" rel="stylesheet" type="text/css" media="all">
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }>
    </script>
    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript">
                jQuery(document).ready(function($) {
                    $(".scroll").click(function(event){
                        event.preventDefault();
                        $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
                    });
                });
        </script>
    <!-- //end-smoth-scrolling -->
</head>
<body>
<!--title start here-->
<div class="title-main">
			<a href="user.jsp"><h1>SocialNetwork</h1></a>

</div>
<!--title end here-->
<!--banner start here-->
 <div class="banner">
		<!--chat list start here-->
		<ul class="list-group">
		    <%
        	for(int i = messages.size() - 1; i >= 0; i-- ){
        	%>
		    <li class="list-group-item">
		        <small class="pull-right text-muted"><%=messages.get(i).getDate()%></small>
		        <div>
		            <%
        			if(messages.get(i).getFrom() == friend.getId()){
        			%>
		            <small class="list-group-item-heading text-muted text-primary"><%=friend%></small>
		            <%
        			}else{
        			%>
        			<small class="list-group-item-heading text-muted text-primary"><%=user%></small>
        			<%
        			 }
    				%>
		            <p class="list-group-item-text">
     				  <%=messages.get(i).getContent()%>
		             </p>
		        </div>
		    </li>
		    <%
             }
            %>
		</ul>
		</br>
		<form action=<%=request.getContextPath() + "/send?to=" + request.getAttribute("to")%> method="post">
			<textarea class="response" rows="2" name="content" placeholder="Write a reply..." resize="none"></textarea>
			</br>
            <button>Send reply</button>
            <div class="banner-button">
            	<a href="${pageContext.request.contextPath}/user.jsp">My Profile</a>
                <a href="${pageContext.request.contextPath}/messages">Messages</a>
            </div>
		</form>
		<!--chat list end here-->
</div>
<!--banner end here-->
</body>
</html>

