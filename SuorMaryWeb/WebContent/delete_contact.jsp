<%@page import="java.util.List"%>
<%@page import="it.beije.suormary.web.rubrica.trapani.DBthroughHBM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
<jsp:include page="elencorubrica.jsp"/>

<form>
	<label for="id">Inserisci id contatto da eliminare:</label><br>
	<input type="text" id="id" name="id" ><br>
  	<input type="submit" value="Delete" onclick="alert('Contatto correttamente eliminato')">
</form>

<%
	String idStr = request.getParameter("id");
	int id = 0;
	
	if (idStr != null && !idStr.isEmpty()) {
	    try {
	        id = Integer.parseInt(idStr);
	        DBthroughHBM.deleteContact(id);
	    } catch (NumberFormatException e) {
	        out.println("L'ID inserito non è valido.");
	    }
	}
%>
</p>
<form action="./rubrica" method="post">
	<input type="submit" value="Indietro">
</form>
</body>
</html>