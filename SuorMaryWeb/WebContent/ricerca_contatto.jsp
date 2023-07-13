<%@page import="java.util.List"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.Contact"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.DBthroughHBM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca</title>
</head>
<body style="font-size:20px;">
<p>
<form>
	<label for="value">Valore da cercare:</label><br>
 	<input type="text" id="value" name="value"><br>
 	<input type="submit" value="Ricerca">
</form>

<%
	String search = ""; 
	String trovati = "";
	search = "%" + request.getParameter("value") + "%";
	List<Contact> contacts = DBthroughHBM.findContacts(search);
	
	for(Contact contact : contacts) {
		trovati = contact.toString();
		%><%= trovati %><br/>
		<%}
	if(contacts.isEmpty()){
		out.println("Nessun contatto trovato");
	}%>
		
</p>
<form action="./rubrica" method="post">
	<input type="submit" value="Indietro">
</form>
</body>
</html>