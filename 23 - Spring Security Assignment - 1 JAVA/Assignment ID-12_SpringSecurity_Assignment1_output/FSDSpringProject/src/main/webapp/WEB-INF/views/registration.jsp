<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>


<head>

<style>

.validation-message {
    font-style: normal;
    font-size: 12px;
    color: #FF1C19;
}
</style>


<title>Registration Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/" method="GET">
		<button class="btn btn-md btn-warning btn-block" type="Submit">Go
			To Login Page</button>
	</form>

	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				
				<form autocomplete="off" action="#" action="${pageContext.request.contextPath}/registration" 
							method="post" class="form-horizontal" role="form">
					
					<h2>Registration Form</h2>
					
					<div class="form-group">
						<div class="col-sm-9">
							<div class="form-control" ><input type="radio" name="type" value="ADMIN" >PRINCIPAL</div> 
							<div class="form-control" ><input type="radio" name="type" value="USER" checked >LIBRARIAN</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" name="firstName" placeholder="First Name" class="form-control" required="required" />
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" name="lastName" placeholder="Last Name" class="form-control" required="required" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" name="email" placeholder="Email" class="form-control" required="required" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="password" name="password" placeholder="Password" class="form-control" required="required" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-9">
							<button type="submit" class="btn btn-primary btn-block">Register User</button>
						</div>
					</div>

					<h2>
						<span class="text-success" >${successMessage}</span>
					</h2>

				</form>
			</div>
		</div>
	</div>

</body>
</html>