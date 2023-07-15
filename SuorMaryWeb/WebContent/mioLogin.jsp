<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% HttpSession sessione = request.getSession();
String errore = (String) sessione.getAttribute("errore");
if(errore!=null){
	%><pstyle="color:red"><%= errore%></p>
<% sessione.removeAttribute("errore");
}
%>
<form action="./LogServlet" method="POST">
  <label for="username">Username:</label><br>
  <input type="text" name="username" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Submit">
</form> 


</body>
</html>