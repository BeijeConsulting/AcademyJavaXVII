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
<title>riferimento contatto</title>
</head>
<body>
<%
EntityManager entityManager = JPAmanagerFactory.createEntityManager();
String id = request.getParameter("id");
Contact c = RubricaJPA.findContactById(entityManager,id);
%>
<h1>Crea un riferimento per il contatto</h1>
<form action="./AddContactDetail" method="POST">
  <input type="hidden" name="id_rubrica" value = <%= c.getId() %> />
    <label for = "contatto">Contatto : </label>
  <input type="text" name="contatto" /> <br> <br>
  <label for = "tipo">Tipo : </label>
  <input type="text" name="tipo" /> <br> <br>
    <label for = "label">Label : </label>
  <input type="text" name="label" /> <br> <br>
  <input type="submit" value="crea" />
</form>
</body>
</html>