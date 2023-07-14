<%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<%@page import="it.beije.suormary.bookstore3.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order</title>
</head>
<body>
<%
	HttpSession currSession = request.getSession();
	String email = (String) currSession.getAttribute("email");
	User currUser = BookStoreUtility.loginUser(email);
	
	int userId = currUser.getId();
	 
%>
</body>
</html>