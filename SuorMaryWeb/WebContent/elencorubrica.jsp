<%@page import="java.util.List"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.Contact"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.DBthroughHBM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco Contatti</title>

</head>
<body>
	
<p>
<%
		String elenco ="";
		StringBuilder contatti = new StringBuilder();
		List<Contact> rubrica = DBthroughHBM.listContacts();
		for(Contact contact : rubrica) {
			//contatti.append(contact.toString() + " \n").append(" \n");
			elenco = contact.toString();
			%><%= elenco %> <br/>
			<% } 	
		//elenco=contatti.toString();%>

</p>

</body>
</html>