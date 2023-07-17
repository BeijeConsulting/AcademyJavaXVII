<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update book</title>
</head>
<body>


<h3>UPDATE BOOK</h3>


<%
int idBook = Integer.parseInt(request.getParameter("indexButton"));
System.out.println("ID" + idBook);
Book book = ((ArrayList<Book>) session.getAttribute("allBooks")).get(idBook);
session.setAttribute("book", book);
System.out.println("BOOK" + book.toString());
session.setAttribute("form", "updatebook");%>
<div>
	<form action="./listservlet" name="book" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" value="<%=book.getTitle() %>" readonly><br><br>
		  
		  <label for="description">Description:</label><br>
		  <textarea name="description" readonly><%=book.getDescription()%></textarea><br><br>
		  
		  <label for="author">Author:</label><br>
		  <input type="text" name="author" value="<%=book.getAuthorId() %>"readonly><br><br>
	
		  <label for="editor">Editor:</label><br>
		  <input type="text" name="editor" value="<%=book.getEditor()%>" readonly><br><br>
		  
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