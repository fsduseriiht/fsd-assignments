<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSD ASSIGNMENT - Home Page</title>
</head>
<body>
 
	<!-- <center> -->
		<h2>FSD Assignment</h2>
		
		<table>
		
			<tr>
				<td>a:</td>
				<td colspan="2" >
					<a href="fsd/subject/add">Add a Subject</a>
				</td>
			</tr>
			
			<tr>
				<td>b:</td>
				<td colspan="2" >
					<a href="fsd/book/add">Add a Book</a>
				</td>
			</tr>
			
			<tr>
				<td>c:</td>
				<td>
					<a id="a_sub_id_del" href="#" >Delete a Subject</a>
				</td>
				<td>
					<input id="txt_sub_id_del" onkeyup="addToHrefForDelete(this , 'subject' )" type="text" placeholder="Subject ID" />
				</td>
			</tr>
			
			<tr>
				<td>d:</td>
				<td>
					<a id="a_book_id_del" href="#" >Delete a book</a>
				</td>
				<td>
					<input id="txt_book_id_del" onkeyup="addToHrefForDelete(this , 'book' )" type="text" placeholder="Book ID" />
				</td>
			</tr>
			
			<tr>
				<td>e:</td>
				<td colspan="2" >
					<a href="fsd/book/search">Display All Books</a>
				</td>
			</tr>
			
			<tr>
				<td>f:</td>
				<td>
					<a id="a_book_id" href="fsd/book/search" >Search Book By ID (only Numbers)</a>
				</td>
				<td>
					<input id="txt_book_id" onkeyup="addToHrefForSearch(this , '?criteria=byId' , 'book' )" type="text"  placeholder="Book ID"  />
				</td>
			</tr>
			
			<tr>
				<td>g:</td>
				<td>
					<a id="a_book_title" href="fsd/book/search">Search Book By Name (alpha numeric)</a>
				</td>
				<td>
					<input id="txt_book_title" onkeyup="addToHrefForSearch(this , '?criteria=byTitle' , 'book' )" type="text"  placeholder="Book Title"   />
				</td>
			</tr>
			
			<tr>
				<td>h:</td>
				<td colspan="2" >
					<a href="fsd/subject/search">Display All subjects</a>
				</td>
			</tr>
			
			<tr>
				<td>i:</td>
				<td>
					<a id="a_sub_id" href="fsd/subject/search" >Search Subject By ID (only Numbers)</a>
				</td>
				<td>
					<input id="txt_sub_id" onkeyup="addToHrefForSearch(this , '?criteria=byId' , 'subject' )" type="text" placeholder="Subject ID"   />
				</td>
			</tr>
			
			<tr>
				<td>j:</td>
				<td>
					<a id="a_sub_title" href="fsd/subject/search" >Search Subject By Name (alpha numeric)</a>
				</td>
				<td>
					<input id="txt_sub_title" onkeyup="addToHrefForSearch(this , '?criteria=byTitle' , 'subject' )" type="text" placeholder="Subject Title"   />
				</td>
			</tr>
			
			
			<tr>
				<td>k:</td>
				<td colspan="2" >
					<input id="hidden_Url" type="hidden" value="${pageContext.request.contextPath}" />
					<a href="#" onclick="close_window()" >Exit Application</a>
				</td>
			</tr>
			
		</table>
	<!-- </center> -->
</body>

<script type="text/javascript">
	function addToHrefForSearch(element, criteria, type) {
		var anchorElement = document.getElementById(element.getAttribute("id").replace("txt", "a"));
		var baseHref = "fsd/" + type + "/search/";
		anchorElement.setAttribute("href", baseHref + element.value + criteria);
	}
	
	function addToHrefForDelete(element, type) {
		var anchorElement = document.getElementById(element.getAttribute("id").replace("txt", "a"));
		var baseHref = "fsd/" + type + "/delete/";
		anchorElement.setAttribute("href", baseHref + element.value);
	}
	
	function close_window() {
		if (confirm("Close Window?")) {
			openWin();
		}
	}
	
	function openWin() {
		var closeUrl = document.getElementById("hidden_Url").value + "/close.jsp";
	    myWindow = window.open(closeUrl, "_self", "width=200, height=100");
	    
	    closeWin(myWindow);
	}
	
	function closeWin(myWindow) {
	    myWindow.close();
	}
</script>
</html>