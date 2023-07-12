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
<body style="background-color: black; color: #C6C6C6; font-family: Arial, Helvetica, sans-serif;">
<div style="margin: auto; padding: 20px; text-align: center">
	<h2>Rubrica Contatti</h2>
</div>
<div style="margin: auto; padding: 50px;">
	<table style="border: 1px solid; border-collapse: collapse; width: 50%; margin: auto; background-color: #404040">
		<tr style="border: 1px solid; border-collapse: collapse; text-align: center; background-color: #C6C6C6; color:#2A2A2A">
			<th style="border: 1px solid; border-collapse: collapse; padding: 3px">Nome</th>
			<th style="border: 1px solid; border-collapse: collapse;">Cognome</th>
			<th style="border: 1px solid; border-collapse: collapse;">Telefono</th>
			<th style="border: 1px solid; border-collapse: collapse;">Email</th>
			<th style="border: 1px solid; border-collapse: collapse;">Note</th>
			<th></th>
			<th></th>
		</tr>
		<%
		List<Contact> contacts = JPAUtils.getAllContacts();
		for(Contact c : contacts){ %>
			<tr style="border: 1px solid; border-collapse: collapse;">
				<td style="border: 1px solid; border-collapse: collapse; padding: 3px"><%=c.getFirstName() %></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%=c.getLastName() %></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%for(ContactDetail cd : c.getDetail()){
						if(cd.getType() == 'T'){
					  		out.println(cd.getDetail() + "<br>");
						}
					  }
				%></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%for(ContactDetail cd : c.getDetail()){
						if(cd.getType() == 'E'){
					  		out.println(cd.getDetail());
						}
					  }
				%></td>
				<td style="border: 1px solid; border-collapse: collapse;"><%=c.getNotes() %></td>
				<td style="text-align: center"><a href="./EditServlet?id=<%=c.getId()%>"><button style="background-color: #EBBF66;">Modifica</button></a></td>
				<td style="text-align: center"><a href="#"><button style="background-color: #EB6666;">Elimina</button></a></td>
			</tr>
		<%} %>
	</table>
</div>

</body>
</html>