<%@page import="it.beije.suormary.web.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert User</title>
</head>
<body>

<%
/*
String name = request.getParameter("fname");
String surname = request.getParameter("lname");
String email = request.getParameter("email");
String password = request.getParameter("password");

User user = new User();
if (name != null && name.length() > 0) user.setEmail(email);
user.setName(name);
user.setSurname(surname);
user.setPassword(password);

session.setAttribute("user", user);
*/
%>

<jsp:useBean id="user" class="it.beije.suormary.web.User" scope="session"></jsp:useBean>
<jsp:setProperty name="user" property="name" param="fname"/>
<jsp:setProperty name="user" property="surname" param="lname"/>
<jsp:setProperty name="user" property="email"/>
<jsp:setProperty name="user" property="password"/>

Name: <%= user.getName() %><br/>
Surname: <%= user.getSurname() %><br/>
Email: <%= user.getEmail() %><br/>
Password: <%= user.getPassword() %><br/>

</body>
</html>