<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	
	<label>Scegli tra le seguenti opzioni: </label>
	<input type="submit" value="Visualizza lista contatti" name="listaContatti"/>
	<input type="submit" value="Cerca contatto" name="cercaContatto"/>
	<input type="submit" value="Inserisci nuovo contatto" name="inserisciContatto"/>
	<input type="submit" value="Modifica contatto" name="modificaContatto"/>
	<input type="submit" value="Cancella contatto" name="cancellaContatto"/>
	<input type="submit" value="Trova contatti duplicati" name="contattiDuplicati"/>
	<input type="submit" value="Unisci contatti duplicati" name="unisciContatti"/>
	<input type="submit" value="Esci" name="esci"/>
	
	<a href="./rubricaServlet"><button value="button">Prova</button></a>
</div>
</body>
</html>