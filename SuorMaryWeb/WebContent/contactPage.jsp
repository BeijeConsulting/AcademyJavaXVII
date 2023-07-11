<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="it.beije.suormary.web.ceccarelli.Contact"%>
<%@page import="java.util.List"%>
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
<label>LISTA CONTATTI</label>
	<%
		List<Contact> contacts = (List<Contact>) (session.getAttribute("contacts"));
	if(contacts== null) {
		%> <h1 style="text-color: red">NON CI SONO CONTATTI DA MOSTRARE</h1>	
	<%
	}else {
		for(Contact c : contacts){
			%><p><%=c.toString() %></p><%
		}
	}
	 
	%>
	
</div>

</body>
</html>