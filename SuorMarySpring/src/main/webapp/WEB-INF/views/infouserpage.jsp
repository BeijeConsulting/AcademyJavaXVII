<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
<form:form action="infouser" method="GET">
<input type="submit" value="Profile" disabled>
</form:form> 

<form:form action="booklist" method="GET">
<input type="submit" value="Buy book">
</form:form> 

<form:form action="stock" method="GET">
<input type="submit" value="Stock">
</form:form> 

<div>
<h2>Info user</h2>
	Name: ${user.name}<br/>
	Surname: ${user.surname}<br/>
	Email: ${user.email}<br/>
</div>

<br/>
<form:form action="logout" method="GET">
<input type="submit" value="Log out">
</form:form> 

<br/>
<div>
<h2>Orders</h2>

</div>
</body>
</html>