<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign up</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/form.css" rel="stylesheet" type="text/css" media="all">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-2.1.0.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
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
			<a href="${pageContext.request.contextPath}/login.jsp"><h1>SocialNetwork</h1></a>

</div>
 <div class="banner">
		<div class="form">
		  <form action="${pageContext.request.contextPath}/signUp" method="post">
                    <input type="text" name="name" placeholder="Name"/>
                    <input type="text" name="lastName" placeholder="Last Name"/>
                    <div class="row">
                      <div class="col-xs-12">
                        <div class="btn-group btn-group-vertical" data-toggle="buttons">
                          <label class="btn active">
                            <input type="radio" name="sex" value="M" checked><i class="fa fa-circle-o fa-2x"></i><i class="fa fa-dot-circle-o fa-2x"></i> <span>  Male</span>
                          </label>
                          <label class="btn">
                            <input type="radio" name="sex" value="F"><i class="fa fa-circle-o fa-2x"></i><i class="fa fa-dot-circle-o fa-2x"></i><span> Female</span>
                          </label>
                          </div>
                      </div>
                    </div>
                    <input type="date" name="birth"/>
                    <input type="text" name="email" placeholder="Email"/>
                    <input type="password" name="password" placeholder="Password"/>
                    <button>Sign Up</button>
                    <p class="message">Already registered? <a href="${pageContext.request.contextPath}/login.jsp">Sign In</a></p>
                  </form>
		 </div>
</body>
</html>