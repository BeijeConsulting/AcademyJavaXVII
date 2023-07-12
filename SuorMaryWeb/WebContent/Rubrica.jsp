<%@page import="it.beije.suormary.web.mancuso.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div style="margin: auto; padding: 50px;">
	<table>
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Telefono</th>
			<th>Email</th>
			<th>Note</th>
		</tr>
		<%for(Contact c: contacts){ %>
		
		
		<%} %>
	</table>
</div>

</body>
</html>