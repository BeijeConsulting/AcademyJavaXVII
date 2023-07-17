<%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="it.beije.suormary.bookstore3.Author"%>
<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>WELCOME</title>
 
</head>
<body>
<%@ include file="header.jsp" %>
<%
HttpSession currSession = request.getSession();
List<Book> books = (List) currSession.getAttribute("books");
%>
<h1>Welcome <%= currSession.getAttribute("email") %></h1>
<form action="./createBook" method ="GET">
<input type="submit" value="aggiungi un libro" class="button">
</form>
<br/>
<form action="newOrder" method ="GET">
<input type="submit" value="crea un ordine" class="button">
</form>

<br/>
<form action="myOrders" method ="GET">
<input type="submit" value="I miei ordini" class="button">
</form>
 <h2>Libri disponibili:</h2>
    <% 
    for (Book b : books) {
    	Author author = BookStoreUtility.getAuthorById(b.getAuthorId());
    %>
        <div class="card">
            <h3><%= "Titolo : " + b.getTitle() %></h3>
            <h4><%="Autore : " +  author.getName()%></h4>
               <%
              if(b.getDescription() != null){
            	  
             %>
             <span class="desc">Descrizione : </span>
             <span><%= b.getDescription() %></span>
             <%
              }
             %>
             <%
             if(session.getAttribute("email") != null){
            	 %> 
             
            <div class="buttons">
                <form action="updateBook" method="GET">
                    <input type="hidden" name="id" value="<%= b.getId() %>" />
                    <input type="submit" value="Modifica libro" />
                </form>
                <form action="deleteBook" method="GET">
                    <input type="hidden" name="id" value="<%= b.getId() %>" />
                    <input type="submit" value="Elimina libro" />
                </form>
            </div>
            <%
    		}
            %>
        </div>
    <% 
    }
    %>


</body>
</html>