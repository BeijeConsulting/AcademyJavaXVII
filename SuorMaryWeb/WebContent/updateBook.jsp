<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.bookstore3.Book"%>
    <%@page import="it.beije.suormary.bookstore3.Author"%>
    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica libro</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Modifica libro</h1>
<%
HttpSession currSession = request.getSession();
 Book book = (Book) currSession.getAttribute("book"); 
 List<Author> authors = (List) session.getAttribute("authors");
%>
<form action="updateBook" method="POST">
<label for="title">Titolo : </label>
<input type="text" name="title" value=<%= book.getTitle() %>/> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" value=<%= book.getDescription() %>/> <br> <br>
<label for="editor">Editore : </label> 
<input type="text" name="editor" value=<%= book.getEditor() %>/> <br> <br>
<label for="price">Prezzo : </label>
<input type="text" name="price" value=<%= book.getPrice() %>/> <br> <br>
<label for="quantity">Quantità : </label>
<input type="text" name="quantity" value=<%= book.getQuantity() %>/> <br> <br>
<label for="quantity">Scegli un autore : </label> <br>
<%
for(Author author : authors){
	%>
	<%
	 if(author.getId() == book.getAuthorId()){
		    
	    
	%>
	<label for ="authorId"><%=author.getName() %></label> 
	<input type="radio"  name="authorId" value=<%= author.getId() %> checked> <br>
	<%
     }
	 else {
		 %>
		<label for ="authorId"><%=author.getName() %></label> 
<input type="radio"  name="authorId" value=<%= author.getId() %>> <br> 
	<% 
	 }
	%>



<%
}
%>
<input type="submit" value="Aggiungi libro" />
</form>
</body>
</html>