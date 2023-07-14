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
	<label>DATI CONTATTO DA RICERCARE</label>
	<!-- con JDBC contatto/i ricercato/i-->
	<form action="./rubricaServlet" method="POST">
		<select name="typeSelection" >
			 <option value="searchForName">Ricerca per nome</option>
			 <option value="searchForSurname">Ricerca per cognome</option>
			 <option value="searchForNameSurname">Ricerca per nome e per cognome</option>
		</select>
	</form>

</div>

</body>
</html>