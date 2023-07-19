<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
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
	<div class="container">
    <h1>Subscribe here</h1>
    <form action="./bookstore_registration" method="POST">
        <div>
            <label for="email"><b>Email:</b></label><br>
            <input type="email" placeholder="Enter email" name="email" id="email" required><br>
        
            <label for="password"><b>Password:</b></label><br>
            <input type="password" placeholder="Enter password" name="password" id="password" required><br>
            
            <label for="name"><b>Name:</b></label><br>
            <input type="text" placeholder="Enter name" name="name" id="name" required><br>
            
            <label for="surname"><b>Surname:</b></label><br>
            <input type="text" placeholder="Enter surname" name="surname" id="surname" required><br>
            
            <div class="button-container">
                <p>Already have an account? <a href="bookstore_login">Sign in</a></p>
                <button type="submit">Registration</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>>