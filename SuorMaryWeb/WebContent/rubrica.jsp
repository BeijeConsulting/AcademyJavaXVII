<%@page import="it.beije.suormary.web.mancuso.JPAUtils"%>
<%@page import="it.beije.suormary.web.mancuso.ContactDetail"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.suormary.web.mancuso.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rubrica</title>
</head>
<body style="background-color: #0E0E0E; color: #C6C6C6; font-family: Arial, Helvetica, sans-serif;">
<div style="margin: auto; padding: 20px; text-align: center">
	<h2>Rubrica Contatti</h2>
</div>
<%
String message = (String) session.getAttribute("message");
if (message != null) {
	%>
	<div style="margin: auto; padding: 20px; text-align: center">
		<p style="color:red"><%= message %></p>
	</div>
	
	<%
	
	session.removeAttribute("message");
}
%>

<div style="margin: auto; padding: 50px; background-color: #404040">
	
	<table style="border: 1px solid; border-collapse: collapse; width: 50%; margin: auto; background-color: #232323">
		<tr style="border: 1px solid #C6C6C; border-collapse: collapse; text-align: center; background-color: #C6C6C6; color:#2A2A2A">
			<th style="border: 1px solid #C6C6C; border-collapse: collapse; padding: 3px">Nome</th>
			<th style="border: 1px solid #C6C6C; border-collapse: collapse;">Cognome</th>
			<th style="border: 1px solid #C6C6C; border-collapse: collapse;">Telefono</th>
			<th style="border: 1px solid #C6C6C; border-collapse: collapse;">Email</th>
			<th style="border: 1px solid #C6C6C; border-collapse: collapse;">Note</th>
			<th></th>
			<th><a href="./NewContactServlet"><button style="background-color: #A8DC9A">Nuovo contatto</button></a></th>
		</tr>
		<%
		List<Contact> contacts = JPAUtils.getAllContacts();
		for(Contact c : contacts){ %>
			<tr style="border: 1px solid; border-collapse: collapse;">
				<td style="border: 1px solid; border-collapse: collapse; padding: 3px"><%=c.getFirstName() %></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%=c.getLastName() %></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%for(ContactDetail cd : c.getDetail()){
						if(cd.getType() == 'T'){
					  		out.println(cd.getDetail() + "<br/>");
						}
					  }
				%></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%for(ContactDetail cd : c.getDetail()){
						if(cd.getType() == 'E'){
					  		out.println(cd.getDetail() + "<br/>");
						}
					  }
				%></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%=c.getNotes() %></td>
				<td style="text-align: center"><a href="./EditServlet?id=<%=c.getId()%>"><button style="background-color: #EBBF66;">Modifica</button></a></td>
				<td style="text-align: center">
					<form method="POST" action="./RubricaServlet">
						<input type="hidden" value="<%=c.getId() %>" name="idContatto" />
						<input type="submit" style="background-color: #EB6666;" name="deleteContact" value="Elimina"/>
					</form>
				</td>
			</tr>
		<%} %>
	</table>
</div>

</body>
</html>