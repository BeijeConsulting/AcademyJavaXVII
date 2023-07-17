<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>NEW BOOK</h1>
<div>
	<form action="./listservlet" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" required><br>
		  <label for="description">Description:</label><br>
		  <input type="description" name="description" id="id">
		  <select id="author" required>
		  <%List<Author> authors = (ArrayList<Author>)(session.getAttribute("allAuthors")); 
		  for(int i=0;i<authors.size();i++){
			 %> <option><%=authors.get(i).getSurname() %></option>
		  <% }%>
		  </select>
		  <label for="editor">Editor:</label><br>
		  <input type="editor" name="editor" id="id">
		  <label for="price">price:</label><br>
		  <input type="price" name="price" id="id" required>
		  <label for="quantity">quantity:</label><br>
		  <input type="quantity" name="quantity" id="id" required>
		  <input type="submit" value="Login">		
	</form>
	<br>
</div>

</body>
</html>