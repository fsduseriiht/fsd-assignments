<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSD ASSIGNMENT - Show Books</title>
</head>
<body>
	<%-- <center> --%>
	<h2>Showing all the books in repository...</h2>
		<c:choose>
			<c:when test = "${operation eq 'delete'}" >
				<h5>Book with ID ${id} is deleted...</h5>
			</c:when>
			<c:otherwise>
				<h2>Search criteria chosen is ${criteria}</h2>
			</c:otherwise>
		</c:choose>
		<table border="1">
				<tr>
					<td>Book Id</td>
					<td>Book Title</td>
					<td>Price</td>
					<td>Volume</td>
					<td>Publish Date</td>
				</tr>
			<c:forEach items="${bookDTOListFromDB}" var="book">
				<tr>
					<td><c:out value="${book.bookId}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td><c:out value="${book.volume}" /></td>
					<td><c:out value="${book.publishDate}" /></td>
				</tr>
			</c:forEach>
			<%-- <c:forEach items="${booksFromFile}" var="book">
				<tr>
					<td><c:out value="${book.bookId}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td><c:out value="${book.volume}" /></td>
					<td><c:out value="${book.publishDate}" /></td>
				</tr>
			</c:forEach> --%>
		</table>
	<%-- </center> --%>
</body>
</html>