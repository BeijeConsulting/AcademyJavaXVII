<%@page import="it.beije.suormary.bookstore1.Author"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
<%List<Author> la = (List<Author>) session.getAttribute("listAuthor"); %>
<div style="text-align: center">
<h1 style="font-family: fantasy; font-size: 36px">BOOKstoreONE</h1>
<%
String newBookMessage = (String) session.getAttribute("newBookMessage");
if (newBookMessage != null) {
	%>
	<p style="color:green"><%= newBookMessage %></p>
	<%
	
	session.removeAttribute("newBookMessage");
}
%>
<h2>Aggiungi un nuovo libro!</h2>
<form action="./BookServlet" method="POST">
  <label for="title">Titolo:</label><br>
  <input type="text" name="title" ><br>
  <label for="description">Descrizione:</label><br>
  <input type="text" name="description" ><br>
  <label for="editor">Editore:</label><br>
  <input type="text" name="editor" ><br>
  <label for="description">Prezzo:</label><br>
  <input type="text" name="price" ><br>
    <label for="quantity">Quantità:</label><br>
  <input type="text" name="quantity" ><br><br>
   <label for="author">Autore:</label><br>
   <select name="author">
   <% for(Author a:la){
	   %>
	   <option value="<%=a.getId() %>"><%=a.getName() %> <%=a.getSurname() %></option>
  <% }%>
   </select>
  <input type="submit" value="Submit">
</form> 
<%session.removeAttribute("listAuthor"); %>
<br><br>
<a href="./LoginServlet">Torna al login</a>
</div>
</body>
</html>