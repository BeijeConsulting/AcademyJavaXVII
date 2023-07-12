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
	String strId = (String)(request.getAttribute("id"));
	System.out.println(strId);
	int id = Integer.valueOf(strId); 
	   Contact contact = JPAUtils.getContact(id);
	%>
	<div style="margin: auto; padding: 50px;">
		<form method="POST" action="./modificaServlet">
			<input type="text" name="nome" value="<%=contact.getFirstName()%>"/>
		</form>
	</div>
</body>
</html>