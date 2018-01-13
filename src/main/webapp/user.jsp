<%@ page language="java" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%User user = (User) request.getSession().getAttribute("user");%>
<!DOCTYPE HTML>
<html>
<head>
    <title>User</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/form.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
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
<div class="title-main">
			<a href="${pageContext.request.contextPath}/user.jsp"><h1>SocialNetwork</h1></a>

</div>
<div class="banner">
		<img src="images/logo.png" alt=""/>
		<h3><%=user%></h3>
		<div class="banner-button">
			<a href="${pageContext.request.contextPath}/friends">Friends</a>
		</div>
		<div class="banner-button">
			<a href="${pageContext.request.contextPath}/messages">Messages</a>
		</div>
</div>
</body>
</html>