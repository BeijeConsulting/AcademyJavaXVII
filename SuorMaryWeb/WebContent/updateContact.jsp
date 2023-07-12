<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="it.beije.suormary.web.Char.RubricaJPA" %>
     <%@page import="javax.persistence.EntityManager" %>
     <%@page import="it.beije.suormary.web.Char.JPAmanagerFactory" %>
      <%@page import="it.beije.suormary.web.Char.Contact" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica contatto</title>
</head>
<body>
<h1>Modifica contatto</h1>
<%
EntityManager entityManager = JPAmanagerFactory.createEntityManager();
String id = request.getParameter("id");
Contact c = RubricaJPA.findContactById(entityManager,id);
%>
<form action="updateContact" method="POST">
<input type="hidden" name="id" value="<%= c.getId() %>">
	<label for="name">Nome : </label>
 	<input type="text" name="name" value=<%= c.getName() %>> <br> <br>
 	<label for="surname">Cognome : </label>
 	<input type="text" name="surname" value=<%= c.getSurname() %>> <br> <br>
	<label for="email">Email : </label> 
	<input type="text" name="note" value=<%= c.getNote() %>> <br>  <br>
	<input type="submit" value="Modifica">
</form>
</body>
</html>