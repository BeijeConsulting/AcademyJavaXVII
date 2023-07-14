<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.bookstore.Author"%>
    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi un libro</title>
</head>
<body>

<%
HttpSession currSession = request.getSession();
List<Author> authors = (List) session.getAttribute("authors");
%>
<h1>Aggiungi un libro</h1>
<form action="createBook" method="POST">
<label for="title">TItolo : </label>
<input type="text" name="title" /> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" /> <br> <br>
<label for="editor">Editore : </label> 
<input type="text" name="editor" /> <br> <br>
<label for="price">Prezzo : </label>
<input type="number" name="price" /> <br> <br>
<label for="quantity">Quantit� : </label>
<input type="number" name="quantity" /> <br> <br>
<label for="quantity">Scegli un autore : </label> <br>
<%
for(Author author : authors){
%>
<label for ="author_id"><%=author.getName() %></label> 
<input type="radio"  name="author_id" value=<%= author.getId() %>> <br>
<%
}
%>
</form>
</body>
</html>