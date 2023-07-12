<%@page import="it.beije.suormary.web.User"%>
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

/*
User user = (User) session.getAttribute("user");
if (user == null) {
	user = new User();
	session.setAttribute("user", user);
}
*/
%>
<<<<<<< HEAD
<%= welcome %> <%= nome %> !!
=======

<jsp:useBean id="user" class="it.beije.suormary.web.User" scope="session"></jsp:useBean>
<%= welcome %> <jsp:getProperty property="name" name="user"/> <jsp:getProperty property="surname" name="user"/> !!
>>>>>>> refs/remotes/origin/main
</p>
</body>
</html>