<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update book</title>
</head>
<body>


<h3>UPDATE BOOK</h3>


<% Book book = (Book) session.getAttribute("book");
session.setAttribute("form", "updatebook");%>
<div>
	<form action="./listservlet" name="book" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" readonly><%=book.getTitle() %><br><br>
		  
		  <label for="description">Description:</label><br>
		  <textarea name="description" readonly><%=book.getDescription()%></textarea><br><br>
		  
		  <label for="author">Author:</label><br>
		  <input type="text" name="author" readonly><%=book.getAuthorId() %><br><br>
	
		  <label for="editor">Editor:</label><br>
		  <input type="text" name="editor" readonly><%=book.getEditor()%><br><br>
		  
		  <label for="price">Price:</label><br>
		  <input type="number" step="0.01" name="price" placeholder="00.00" min="0.00" value="<%=book.getPrice()%>"><br><br>
		  
		  <label for="quantity">Quantity:</label><br>
		  <input type="number" name="quantity" min="1" value="<%=book.getQuantity()%>"><br><br>
		  
		  <input type="submit" value="Save">		
	</form>
	<br>
	
</div>
</body>
</html>