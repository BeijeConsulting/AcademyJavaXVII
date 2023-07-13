<%@page import="java.util.List"%>
<%@page import="web.rubrica.caroselli.Contact"%>
<%@ page import="web.rubrica.caroselli.RubricaUtilsJPA"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista di tutti i contatti</title>
</head>
<body>
	<p>Hello world</p>
	
	<% List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");%> 
	


	<%
	if (contacts != null) {
		
		for (Contact contact : contacts) {
	%>
	<p>
		Element:
		<%=contact.getId()%>
		<%=contact.getName()%>
		<%=contact.getSurname()%>
		<%=contact.getNote()%>
		<%=contact.getDetails()%>
	</p>
	<%
	}
	}
	%>

</body>
</html>