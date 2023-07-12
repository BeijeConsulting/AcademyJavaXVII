<%@page import="it.beije.suormary.web.mancuso.ContactDetail"%>
<%@page import="it.beije.suormary.web.mancuso.JPAUtils"%>
<%@page import="it.beije.suormary.web.mancuso.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica</title>
</head>
<body style="background-color: black; color: #C6C6C6; font-family: Arial, Helvetica, sans-serif;">
	<% 
	   Contact contact = (Contact)request.getAttribute("contact");
	%>
	<div style="margin: auto; padding: 25px;">
		<h2>Modifica Contatto</h2>
	</div>
	<% if(request.getAttribute("message") != null){ %>
	<div style="margin: auto; padding: 25px;">
		<p style="color: red"><%=request.getAttribute("message")%></p>
	</div>
	<%} %>
	<div style="margin: auto; padding: 50px; background-color: #404040">
		<form method="POST" action="./EditServlet">
			<input type="hidden" name="id" value="<%=contact.getId() %>" />
			<table>
				<tr >
					<td style="padding: 10px;"><label>Nome</label></td>
					<td><input type="text" name="nome" style="background-color: #2A2A2A; color: #C6C6C6" value="<%=contact.getFirstName()%>"/></td>
				</tr>
				<tr>
					<td style="padding: 10px;">Cognome</td>
					<td><input type="text" name="cognome" style="background-color: #2A2A2A; color: #C6C6C6" value="<%=contact.getLastName()%>"/></td>
				</tr>
				<tr>
					<td style="padding: 10px;">Note</td>
					<td><textarea name="note" cols=30 rows=5 style="background-color: #2A2A2A; color: #C6C6C6"><%=contact.getNotes()%></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: right"><input style="width: 100px; text-align: center; font-size: 16px;" type="submit" name="saveContact" value="Salva" /></td>
				</tr>
			</table>
			
		</form>
	</div>
	<div style="margin: auto; padding: 25px; background-color: #404040">
		<%for(ContactDetail cd : contact.getDetail()){%>
			<div style="display: inline-block; *display: inline; zoom: 1; vertical-align: top; padding: 10px; margin-right: 15px; border: 1px solid;">
				<b>Ref. Contatto</b>
				<hr/>
				<br/>
				<form method="POST" action="./EditServlet">
				<input type="hidden" name="id" value="<%=contact.getId() %>" />
				<input type="hidden" name="idRef" value="<%=cd.getId() %>" />
				<label>Label</label>
				<input style="float: right; background-color: #2A2A2A; color: #C6C6C6" name="label" type="text" value="<%=cd.getLabel()%>"/><br/><br/>
				<label style="margin-right: 10px">Contatto</label>
				<input style="float: right; background-color: #2A2A2A; color: #C6C6C6" name="detail" type="text" value="<%=cd.getDetail()%>"/><br/><br/>
				<label>Tipo</label><br/>
				<input type="radio" value="T" name="type" <%=(cd.getType() == 'T'?"checked":"")%>/>
				<label>Telefono</label><br/>
				<input type="radio" value="E" name="type" <%=(cd.getType() == 'E'?"checked":"")%>/>
				<label>Email</label><br/><br/>
				<input style="width: 100px; text-align: center; font-size: 16px; float:right" name="saveRef" type="submit" value="Salva" />
			</form>
			</div>
				
		<%}%>
		
		
	</div>
</body>
</html>