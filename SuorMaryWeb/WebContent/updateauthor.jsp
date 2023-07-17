<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update author</title>
</head>
<body>

<% Author author = (Author) session.getAttribute("author");
session.setAttribute("form", "updateauthor");%>

<h3>UPDATE AUTHOR</h3>

<div>
	<form action="./listservlet" name="author" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" readonly><%=author.getName() %><br><br>
		  
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" readonly><%=author.getName() %><br><br>
		  
		  <label for="description">Description:</label><br>
		  <textarea name="description"><%=author.getDescription()%></textarea><br><br>
	</form>
	<br>

</div>


</body>
</html>