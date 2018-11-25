<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <title>Library Management System</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<style>

.wrapper {
    margin-top: 80px;
    margin-bottom: 20px;
}

.form-signin {
    max-width: 420px;
    padding: 30px 38px 66px;
    margin: 0 auto;
    background-color: #eee;
    border: 3px dotted rgba(0,0,0,0.1);
}

.form-signin-heading {
    text-align:center;
    margin-bottom: 30px;
}

.form-control {
    position: relative;
    font-size: 16px;
    height: auto;
    padding: 10px;
}

input[type="text"] {
    margin-bottom: 0px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
}

input[type="password"] {
    margin-bottom: 20px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
</style>
</head>

<body>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <button class="btn btn-md btn-warning btn-block" type="Submit">Go To Registration Page</button>
</form>

<div class="container">
    <img src="https://creativevfx.net/wp-content/uploads/2018/04/login_members_homepageBottom.svg" class="img-responsive center-block" width="200" height="200" alt="Logo" style="padding-bottom: 30px;  padding-top: 30px;"/>
    <form action="${pageContext.request.contextPath}/welcome" method="POST" class="form-signin">
        <h3 class="form-signin-heading">Welcome</h3>
        <br/>

        <input type="text" id="email" name="email" placeholder="Email" class="form-control" required="required"/> <br/>
        <input type="password" placeholder="Password" id="password" name="password" class="form-control" required="required" /> <br/>

		<c:if test = "${param.error}">
			<div align="center" >
			    <p style="font-size: 20; color: #FF1C19;">Email or Password invalid, please verify</p>
			</div>
		</c:if>
        
        <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit">Login</button>
    </form>
</div>
</body>
</html>