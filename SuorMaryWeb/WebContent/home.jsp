<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.web.Char.RubricaJPA" %>
     <%@page import="javax.persistence.EntityManager" %>
     <%@page import="it.beije.suormary.web.Char.JPAmanagerFactory" %>
      <%@page import="java.util.List" %>
      <%@page import="it.beije.suormary.web.Char.Contact" %>
      <%@page import="it.beije.suormary.web.Char.ContactDetail" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Rubrica</title>
</head>
<body>
<h1>Lista contatti</h1>
<form action="./newContact.jsp" method="GET">
<input type="submit"  value="Crea nuovo contatto"> <br>
</form>

<%
EntityManager entityManager = JPAmanagerFactory.createEntityManager();
List<Contact> contacts = RubricaJPA.loadRubricaJPA(entityManager);
%>
<% 
for(Contact c : contacts){
	%>
	<h2><%= c.getName() + " " + c.getSurname() %></h2>
	<%
	for(ContactDetail cd : c.getContactDetails()){
		out.println("<div class='cont'>");
		if(cd.getTipo().equals('T')) out.print("<span>" + "Telefono " + cd.getLabel() + " : " + cd.getContatto() + "</span>");
		else if (cd.getTipo().equals('E')) out.print("<span>" + "Email " + cd.getLabel() + " : " + cd.getContatto()  + "</span>");
		else  out.print("<h4>" + cd.getLabel() + " : " +  cd.getContatto() + "</h4>");
		%>
		<form action="updateContactDetail.jsp" method= "GET">
		<input type="hidden" name="id" value="<%= cd.getId() %>">
		<input type="submit" value="Modifica riferimento" class="button"/> <br> <br>
		</form>
		<form action="DeleteContactDetail" method= "GET">
		<input type="hidden" name="id" value="<%= cd.getId() %>">
		<input type="submit" value="Elimina riferimento" class="button"/> <br> <br>
		</form>
		<%
		out.println("</div>");
	}
		%>
	
	
	<form action="./addContactDetail.jsp" method="GET">
<input type="hidden" name="id" value="<%= c.getId() %>">
<input type="submit" value="Aggiungi un riferimento"> <br> <br>
</form>
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


%>

</body>
</html>