<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>

<p>
<%
String nome = (String) session.getAttribute("nome");
if (nome == null) response.sendRedirect("login");

String welcome = "BUONGIORNO";
%>
<%= welcome %> <%= nome %> !!
</p>
</body>
</html>