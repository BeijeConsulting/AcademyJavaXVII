<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index JSP</title>
</head>
<body>

<p>
<%
/*
LocalDateTime now = LocalDateTime.now();
List<String> list = null; //....
String name = request.getParameter("name");
System.out.println("Sono in index.jsp");
*/
//out.print(welcome + " " + name);

String welcome = "BUONGIORNO";

System.out.println("prova");
//out.print(welcome + " " + name);

String username = (String) session.getAttribute("username");
%>
<%= welcome %> <%= username %> !!
</p>
</body>
</html>