<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="it.beije.suormary.web.Char.RubricaJPA" %>
     <%@page import="javax.persistence.EntityManager" %>
     <%@page import="it.beije.suormary.web.Char.JPAmanagerFactory" %>
      <%@page import="it.beije.suormary.web.Char.Contact" %>
      <%@page import="it.beije.suormary.web.Char.ContactDetail" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Modifica riferimento</h1>
<%
  String idString = request.getParameter("id");
  int id = Integer.parseInt(idString);
  EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  ContactDetail cd =  RubricaJPA.findContactDetail(entityManager, id);
%>
<form action="UpdateContactDetail" method="POST">
<input type="hidden" name="id" value= <%= cd.getId() %> />
<label for="contatto">Contatto : </label>
<input type="text" name ="contatto" value=<%= cd.getContatto() %> /> <br> <br>
<label for="tipo">Tipo : </label>
<input type="text" name ="tipo" value=<%= cd.getTipo() %> /> <br> <br>
<label for="label">Label : </label>
<input type="text" name ="label" value=<%= cd.getLabel()%> /> <br> <br>
<input type="submit" value="Modifica" />

</form>
</body>
</html>