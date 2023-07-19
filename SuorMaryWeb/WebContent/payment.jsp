<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<!--  
	 * I - inserted
	 * P - paid
	 * C - cancelled
	 * B - basket
-->
<div>
	<form action="./orderservlet" name="payment" method="POST">
		  <label for="address">Shipping address:</label><br>
		  <input type="text" name="address" required><br>
		  <label for="typePayment">Type payment:</label><br>
		  <select name="typePayment" style="width:135pt;" required>
		  	<option value="cash">cash</option>
		  	<option value="card">card</option>
		  </select><br/>
		  <label for="price">Price:</label><br>
		  <input type="number" value="<%=session.getAttribute("basketAmount")%>" readonly><br><br>
		  <input type="submit" value="Add">		
	</form>
	<br>

</div>

</body>
</html>