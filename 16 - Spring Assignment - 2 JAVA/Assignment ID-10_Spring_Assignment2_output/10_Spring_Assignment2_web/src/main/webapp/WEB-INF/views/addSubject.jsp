<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<script type="text/javascript">
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSD ASSIGNMENT - Add Subject Page</title>
</head>
<body>
	<h2>Subject Information</h2>
	<form method="POST" action="${pageContext.request.contextPath}/fsd/subject/add/content">
	<table border="1">
		<tr>
			<td><label id="subjectId">Enter the Subject Id (only numbers) :: </label></td>
			<td><input name="subjectId" type="text" placeholder="Subject Id"  required="required" /></td>
		</tr>
		<tr>
			<td><label id="subtitle">Enter the Subject Title :: </label></td>
			<td><input name="subtitle" type="text" placeholder="Subject Title"  required="required"  /></td>
		</tr>
		<tr>
			<td><label id="durationInHours">Enter the Subject Duration in Hours (only numbers) :: </label></td>
			<td><input name="durationInHours" type="text" placeholder="Duration"  required="required"  /></td>
		</tr>
		<tr>
			<td><label id="booklist">Enter Book ID (only numbers seperated by semi-colon ";") :- </label></td>
			<td><input name="booklist" type="text" placeholder="Book ID of References"  required="required"  /></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
	</form>
	
	<h3>Select the Book references(Book Id) from the menu below (Seperated by semi-colon ";") ::</h3> 
	<table border="1">
			<tr>
				<td>Book Id</td>
				<td>Book Title</td>
				<td>Price</td>
				<td>Volume</td>
				<td>Publish Date</td>
			</tr>
		<c:forEach items="${booksFromFile}" var="book">
			<tr>
				<td><c:out value="${book.bookId}" /></td>
				<td><c:out value="${book.title}" /></td>
				<td><c:out value="${book.price}" /></td>
				<td><c:out value="${book.volume}" /></td>
				<td><c:out value="${book.publishDate}" /></td>
			</tr>
		</c:forEach>
	</table>
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