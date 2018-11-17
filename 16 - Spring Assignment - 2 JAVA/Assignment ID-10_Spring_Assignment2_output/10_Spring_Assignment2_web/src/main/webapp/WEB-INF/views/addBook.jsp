<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<script type="text/javascript">
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSD ASSIGNMENT - Add Book Page</title>
</head>
<body>
	<h2>Book Information</h2>
	<form method="POST" action="${pageContext.request.contextPath}/fsd/book/add/content">
	<table border="1">
		<tr>
			<td><label id="bookId">Enter the Book Id (only numbers) :: </label></td>
			<td><input name="bookId" type="number" placeholder="Book Id" required="required"/></td>
		</tr>
		
		<tr>
			<td><label id="title">Enter the Book title :: </label></td>
			<td><input name="title" type="text"  placeholder="Book title" required="required" /></td>
		</tr>
		
		<tr>
			<td><label id="price">Enter the Book price (only numbers [decimal]) :: </label></td>
			<td><input name="price" type="text" placeholder="Book price"  required="required"  /></td>
		</tr>
		
		<tr>
			<td><label id="volume">Enter the Book Volume (only numbers) ::  </label></td>
			<td><input name="volume" type="text" placeholder="Book Volume" required="required"   /></td>
		</tr>
		
		<tr>
			<td><label id="publishDate">Enter the Book Publish Date (format = "dd-MM-yyyy") :: </label></td>
			<td><input name="publishDate" type="text" placeholder="Publish Date" required="required"   /></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
	</form>
	
</body>

<script type="text/javascript">
	function addToHrefForSearch(element, criteria, type) {
		var anchorElement = document.getElementById(element.getAttribute("id")
				.replace("txt", "a"));
		var baseHref = "fsd/" + type + "/search/";
		anchorElement.setAttribute("href", baseHref + element.value + criteria);
	}

	function addToHrefForDelete(element, type) {
		var anchorElement = document.getElementById(element.getAttribute("id")
				.replace("txt", "a"));
		var baseHref = "fsd/" + type + "/delete/";
		anchorElement.setAttribute("href", baseHref + element.value);
	}
</script>
</html>