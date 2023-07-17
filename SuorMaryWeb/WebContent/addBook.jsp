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
	<form action="./listservlet" name="book" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" required><br><br>
		  <label for="description">Description:</label><br>
		  <textarea name="description"></textarea><br><br>
		  
		  <label for="author">Author:</label><br>
		  <select name="author" style="width:135pt;" required>
		  <%
		  List<Author> authors = (ArrayList<Author>)(session.getAttribute("allAuthors")); 
		  for(int i=0;i<authors.size();i++){
			 %> <option value="<%=authors.get(i).getId()%>"><%=authors.get(i).getSurname()%></option>
		  <% }
		  %>
		  </select>
		  <!-- <a href="addAuthor.jsp">
				<button name="selection" value="addAuthor" >Add author</button>
		  </a> -->
		  <br><br>
		  
		  <label for="editor">Editor:</label><br>
		  <input type="text" name="editor"><br><br>
		  <label for="price">Price:</label><br>
		  <input type="number" step="0.01" name="price" placeholder="00.00" min="0.00" required><br><br>
		  <label for="quantity">Quantity:</label><br>
		  <input type="number" name="quantity" min="1" required><br><br>
		  <input type="submit" value="Add">		
	</form>
	<br>
	<%session.setAttribute("form", "book");%>
</div>

</body>
</html>