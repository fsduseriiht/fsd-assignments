<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSD ASSIGNMENT - Show Subjects</title>
</head>
<body>
	<%-- <center> --%>
	<h2>Showing all the subjects in repository...</h2>
		<c:choose>
			<c:when test = "${operation eq 'delete'}" >
				<h5>Subject with ID ${id} is deleted...</h5>
			</c:when>
			<c:otherwise>
				<h2>Search criteria chosen is ${criteria}</h2>
			</c:otherwise>
		</c:choose>

		<table border="1">
				<tr>
					<td>Subject Id</td>
					<td>Subject Title</td>
					<td>Duration In Hours</td>
					<td>Book References</td>
				</tr>
			<%-- <c:forEach items="${subjectsFromFile}" var="subject">
				<tr>
					<td><c:out value="${subject.subjectId}" /></td>
					<td><c:out value="${subject.subtitle}" /></td>
					<td><c:out value="${subject.durationInHours}" /></td>
					<td>
						<c:forEach items="${subject.references}" var="reference">
							<c:out value="${reference.title}" /> <br>
						</c:forEach>
					</td>
				</tr>
			</c:forEach> --%>
			
			<c:forEach items="${subjectDTOListFromDB}" var="subject">
				<tr>
					<td><c:out value="${subject.subjectId}" /></td>
					<td><c:out value="${subject.subtitle}" /></td>
					<td><c:out value="${subject.durationInHours}" /></td>
					<td>
						<%-- <c:choose>
							<c:when test = "${operation eq 'delete'}" >
								<h5>Subject with ID ${id} is deleted...</h5>
							</c:when>
							<c:otherwise>
								<h2>Search criteria chosen is ${criteria}</h2>
							</c:otherwise>
						</c:choose> --%>
					
						<c:forEach items="${subject.references}" var="reference">
							<c:out value="${reference.title}" /> <br>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>


	<%-- </center> --%>
</body>
</html>