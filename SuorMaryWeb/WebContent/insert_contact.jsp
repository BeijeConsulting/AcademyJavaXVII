<%@page import="it.beije.suormary.web.rubrica.trapani.DBthroughHBM"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserisci nuovo contatto</title>
</head>
<body>
<p>
	<form action="./insert_contact.jsp" method="POST">
	  <label for="fname">Name:</label><br>
	  <input type="text" name="fname" ><br>
	  <label for="lname">Surname:</label><br>
	  <input type="text" name="lname" ><br>
	  <label for="phoneNumber">Phone Number:</label><br>
	  <input type="text" name="phoneNumber" ><br>
	  <label for="email">Email:</label><br>
	  <input type="text" name="email" ><br>
	  <label for="note">Note:</label><br>
	  <input type="text" name="note" ><br>
	  <input type="submit" value="Inserisci"  onclick="alert('Contatto Inserito Correttamente')">
	</form> 
	
	
	<jsp:useBean id="contact" class="it.beije.suormary.web.rubrica.trapani.Contact"></jsp:useBean>
	<jsp:setProperty name="contact" property="name" param="fname"/>
	<jsp:setProperty name="contact" property="surname" param="lname"/>
	<jsp:setProperty name="contact" property="phoneNumber"/>
	<jsp:setProperty name="contact" property="email"/>
	<jsp:setProperty name="contact" property="note"/>
	
	<%
		DBthroughHBM.insertContact(contact);	
	%>
</p>
<form action="./rubrica" method="post">
	<input type="submit" value="Indietro">
</form>
</body>
</html>