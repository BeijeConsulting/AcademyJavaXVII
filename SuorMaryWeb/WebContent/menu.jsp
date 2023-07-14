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
	<a href="./rubricaServlet"><button name="selection" value="listaContatti">Visualizza lista contatti</button></a>
	<a href="./rubricaServlet"><button name="selection" value="cercaContatto">Cerca contatto</button></a>
	<a href="./rubricaServlet"><button name="selection" value="inserisciContatto">Inserisci nuovo contatto</button></a>
	<a href="./rubricaServlet"><button name="selection" value="modificaContatto">Modifica contatto</button></a>
	<a href="./rubricaServlet"><button  name="selection" value="cancellaContatto">Cancella contatto</button></a>
	<a href="./rubricaServlet"><button name="selection" value="contattiDuplicati">Trova contatti duplicati</button></a>
	<a href="./rubricaServlet"><button name="selection" value="unisciContatti">Unisci contatti duplicati</button></a>
	<a href="./rubricaServlet"><button  name="selection">Esci</button></a>
</div>
</body>
</html>