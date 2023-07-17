<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
<div style="text-align: center">
<h1 style="font-family: fantasy; font-size: 36px">BOOKstoreONE</h1>
<h2>Aggiungi un nuovo libro!</h2>
<form action="./BookServlet" method="POST">
  <label for="title">Titolo:</label><br>
  <input type="text" name="title" ><br>
  <label for="description">Descrizione:</label><br>
  <input type="text" name="descritpion" ><br>
  <label for="editor">Editore:</label><br>
  <input type="text" name="editor" ><br>
  <label for="description">Prezzo:</label><br>
  <input type="text" name="price" ><br>
    <label for="quantity">Quantità:</label><br>
  <input type="text" name="quantity" ><br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
</div>
</body>
</html>