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
String username = (String) session.getAttribute("username");
if (username == null) response.sendRedirect("login");

String welcome = "BUONGIORNO";
%>
<%= welcome %> <%= username %> !!
</p>
</body>
</html>