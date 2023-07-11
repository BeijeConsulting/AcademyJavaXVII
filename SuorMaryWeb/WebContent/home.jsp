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
<%
EntityManager entityManager = JPAmanagerFactory.createEntityManager();
List<Contact> contacts =  RubricaJPA.loadRubricaJPA(entityManager);
for(Contact c : contacts){
	out.println("<h2>" +  c.getName() + " " + c.getSurname() +  "</h2>");
}
entityManager.close();

%>
<form action="./newContact.jsp" method="GET">
<input type="submit" value="Crea nuovo contatto">
</form>
</body>
</html>