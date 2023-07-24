<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order summary</title>
<style>
        body {
            font-family: "Roboto", sans-serif;
        }
         .container {
            display: flex;
            align-items: flex-start;
            justify-content: flex-start;
            flex-wrap: wrap; 
            gap: 20px;
            height: 100vh;
            padding: 16px;
        }
        
        .center-button {
        text-align: center;
        width: 100%;
        }
        
        .books {
        	width: 300px;
        	border: 1px solid #ccc; 
        	padding: 10px; 
        	background-color: #fafafa;
        	border-radius: 14px;
        }
        
        .title {
        	text-align: center; 
        	color: #333;
        }
        .paragraph {
        	text-align: center;
        }
        .button {
        background-color: #2c5e29;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        }
        .button_home {
         display: flex;
       	 justify-content: flex-start; 
         gap: 10px;

            
        }
        
        .button[disabled] {
          background-color: #ccc;
          color: #666;
          cursor: not-allowed;
        }
        
        h1 {
        text-align: center;
        }
    </style>
</head>
<body>
<h1>Order summary</h1>
 <div class="button_home">
	<form action="./bookstore_welcome" method="GET">
	   <button class = "button" type="submit">Home</button>
	</form>  
 </div>
<c:choose>
	<c:when test="${not empty books}">
    	<div class="container">
    	<c:forEach items="${books}" var="book" varStatus="loop">
    		<c:set var="author" value="${authors[loop.index]}" />
    		<c:set var="quantity" value="${quantities[loop.index]}" />
    		<c:set var="maxQuantity" value="${book.quantity}" />
    		
    		
    		<div class="books">
            <h2 class="title">${book.title}</h2>
            <hr>
            <p class= "paragraph"><strong>Quantity:</strong>${quantity}</p>
            <p class= "paragraph"><strong>Shipping_address: </strong>${shipping_address}</p>
            
 
            <br>
            </div>
        </c:forEach>
        <div class="center-button">
    	<form action="./bookstore_order_list" method="POST" name="action" value="order">
    	<input type="hidden" name="shipping_address" value="${shipping_address}">
    		<button type="submit" class="button">Order</button>
        </form>
    	</div>
    	</div>
	</c:when>

</c:choose>
</body>
</html>