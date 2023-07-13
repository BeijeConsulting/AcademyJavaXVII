<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuovo Contatto</title>
</head>
<body>
	<body style="background-color: black; color: #C6C6C6; font-family: Arial, Helvetica, sans-serif;">

	<div style="margin: auto; padding: 25px;">
		<h2>Nuovo Contatto</h2>
	</div>
	<div style="margin: auto; padding: 25px;">
		<a href="./RubricaServlet"><button style="background-color: #2A2A2A"><span style="color: #C6C6C6">Indietro</span></button></a>
	</div>
	<%
	String message = (String) session.getAttribute("message");
	if (message != null) {
		%>
		<div style="margin: auto; padding: 25px;">
			<p style="color:red"><%= message %></p>
		</div>
		
		<%
		
		session.removeAttribute("message");
	}
	%>
	<div style="margin: auto; padding: 50px; background-color: #404040">
		<form method="POST" action="./NewContactServlet">
			<table>
				<tr >
					<td style="padding: 10px;"><label>Nome</label></td>
					<td><input type="text" name="nome" style="background-color: #2A2A2A; color: #C6C6C6"/></td>
				</tr>
				<tr>
					<td style="padding: 10px;">Cognome</td>
					<td><input type="text" name="cognome" style="background-color: #2A2A2A; color: #C6C6C6"/></td>
				</tr>
				<tr>
					<td style="padding: 10px;">Note</td>
					<td><textarea name="note" cols=30 rows=5 style="background-color: #2A2A2A; color: #C6C6C6"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: right"><input style="width: 100px; text-align: center; font-size: 16px;" type="submit" name="saveContact" value="Salva" /></td>
				</tr>
			</table>
			
		</form>
	</div>
	
</body>
</html>