<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update author</title>
</head>
<body>

<%
session.setAttribute("form", "updateauthor");

int idAuthor = Integer.parseInt(request.getParameter("indexButton"));
Author author = ((ArrayList<Author>) session.getAttribute("allAuthors")).get(idAuthor);
session.setAttribute("author", author);

%>

<h3>UPDATE AUTHOR</h3>

<div>
	<form action="./listservlet" name="author" method="POST">
		  <label for="name">Name:</label><br>
		  <input type="text" name="name" readonly value="<%=author.getName() %>"><br><br>
		  
		  <label for="surname">Surname:</label><br>
		  <input type="text" name="surname" readonly value="<%=author.getSurname() %>"><br><br>
		  
		  <label for="description">Description:</label><br>
		  <textarea name="description"><%=author.getDescription()%></textarea><br><br>
		  
		  <input type="submit" value="Save">	
	</form>
	<br>

</div>


</body>
</html>