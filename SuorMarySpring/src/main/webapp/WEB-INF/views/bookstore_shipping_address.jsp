<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert the shipping address</title>
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
    <h1>Shipping Address</h1>
    <form action="./bookstore_shipping_address" method="POST">
        <div>
            <label for="street"><b>Street:</b></label><br>
            <input type="text" placeholder="Enter a street" name="street" id="street" required><br>
        
            <label for="city"><b>City:</b></label><br>
            <input type="text" placeholder="Enter city" name="city" id="city" required><br>
            
            <label for="postal_code"><b>Postal code:</b></label><br>
            <input type="text" placeholder="Enter a postal code" name="postal_code" id="postal_code" required><br>
            
            <label for="country"><b>Country:</b></label><br>
            <input type="text" placeholder="Enter a country" name="country" id="country" required><br>
            
            <div class="button-container">
                <button type="submit">Go to order summary</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>