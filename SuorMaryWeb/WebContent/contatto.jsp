<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MenuScelta</title>
</head>
<body>
<p>

<form action="./rubrica" method="POST">
	<input type="radio" id="rubrica" name="scelta" value="Visualizza lista contatti completa">
	<label for="scelta">Visualizza lista contatti completa</label><br>
	<input type="radio" id="rubrica" name="scelta" value="Cerca contatto">
	<label for="scelta">Cerca contatto</label><br>
	<input type="radio" id="scelta" name="scelta" value="Inserisci nuovo contatto">
	<label for="scelta">Inserisci nuovo contatto</label><br>
	<input type="radio" id="scelta" name="scelta" value="Modifica contatto">
	<label for="scelta">Modifica contatto</label><br>
	<input type="radio" id="scelta" name="scelta" value="Cancella contatto">
	<label for="scelta">Cancella contatto</label><br>
	<input type="radio" id="scelta" name="scelta" value="Trova contatti doppi">
	<label for="scelta">Trova contatti doppi</label><br>
	<input type="radio" id="scelta" name="scelta" value="Unisci contatti doppi">
	<label for="scelta">Unisci contatti doppi</label><br>
<input type="submit" value="invio">
</form>


</p>
</body>
</html>