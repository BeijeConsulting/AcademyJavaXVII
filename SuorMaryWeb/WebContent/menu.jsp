<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>
<h2>Menu Rubrica</h2>

<form action="./menu" method="POST">
  <label for="menu">Scegli un'opzione:</label>
  <select id="menu" name="menu">
    <option value="01">Visualizza lista contatti</option>
    <option value="02">Cerca contatto</option>
    <option value="03">Inserisci nuovo contatto</option>
    <option value="04">Modifica contatto</option>
    <option value="05">Cancella contatto</option>
    <option value="06">Trova contatti duplicati</option>
    <option value="07">Unisci contatti duplicati</option>
  </select>
  <input type="submit" value="Submit">
</form>
</body>
</html>