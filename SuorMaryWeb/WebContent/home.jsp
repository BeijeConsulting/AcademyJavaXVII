<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.web.Char.RubricaJPA" %>
     <%@page import="javax.persistence.EntityManager" %>
     <%@page import="it.beije.suormary.web.Char.JPAmanagerFactory" %>
      <%@page import="java.util.List" %>
      <%@page import="it.beije.suormary.web.Char.Contact" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rubrica</title>
</head>
<body>
<h1>Lista contatti</h1>
<form action="./newContact.jsp" method="GET">
<input type="submit"  value="Crea nuovo contatto"> <br>
</form>
<%
EntityManager entityManager = JPAmanagerFactory.createEntityManager();
List<Contact> contacts =  RubricaJPA.loadRubricaJPA(entityManager);
%>
<% 
for(Contact c : contacts){
	out.println("<h2>" +  c.getName() + " " + c.getSurname() +  "</h2>");
%>
<form action="./updateContact.jsp" method="GET">
<input type="hidden" name="id" value="<%= c.getId() %>">
<input type="submit" value="Modifica Contatto"> <br> <br>
</form>
<form action="./deleteContact" method="GET">
<input type="hidden" name="id" value="<%= c.getId() %>">
<input type="submit" value="Elimina Contatto">
</form>
<% 
}
entityManager.close();

%>

</body>
</html>