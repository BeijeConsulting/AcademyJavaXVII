<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style>
body {
    font-family: "Roboto", sans-serif;
    background-color: #fafafa;
}

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

h1 {
    font-size: 24px;
    color: #333;
    margin-bottom: 16px;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

input {
    margin-bottom: 8px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    width: 200px;
}

a {
    color: #2196f3;
    text-decoration: none;
}

.button-container {
    text-align: center;
    width: 100%;
}

button {
    background-color: #04AA6D;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 4px;
    cursor: pointer;
    width: 200px;
    margin-top: 16px;
}

</style>
</head>
<body>

<%
String loginError = (String) session.getAttribute("loginError");
if (loginError != null) {
	%>
	<p style="color:red"><%= loginError %></p>
	<%
	
	session.removeAttribute("loginError");
}
%>
<div class="container">
    <h1>Sign In</h1>
    <form action="./bookstore_login" method="POST">
        <div>
            <label for="email"><b>Email:</b></label><br>
            <input type="email" placeholder="Enter email" name="email" id="email" required><br>
        
            <label for="password"><b>Password:</b></label><br>
            <input type="password" placeholder="Enter password" name="password" id="password" required><br>
            
            <div class="button-container">
                <p>Not a member yet? <a href="bookstore_registration">Sign up</a></p>
                <button type="submit">Login</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>