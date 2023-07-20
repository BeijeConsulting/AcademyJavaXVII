<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
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
        
        .button[disabled] {
          background-color: #ccc;
          color: #666;
          cursor: not-allowed;
        }
    </style>
</head>
<body>
<h1>Cart</h1>
<c:choose>
	<c:when test="${not empty books}">
    	<div class="container">
    	<c:forEach items="${books}" var="book" varStatus="loop">
    		<c:set var="author" value="${authors[loop.index]}" />
    		<c:set var="quantity" value="${quantities[loop.index]}" />
    		<c:set var="maxQuantity" value="${book.quantity}" />
    		<c:set var="addButtonDisabled" value="${quantity >= maxQuantity}" />
    		
    		<div class="books">
            <h2 class="title">${book.title}</h2>
            <hr>
            <p class= "paragraph"><strong>Author:</strong>${author.name} ${author.surname}</p>
            <p class= "paragraph"><strong>Quantity:</strong>${quantity}</p>
            
            <c:choose>
            	<c:when test="${addButtonDisabled}">
            		<form class="paragraph">
                	<button type="submit" class="button" 
                	title="Maximum amount of books reached for this book."
                	disabled>Add</button>
        			</form>
        		</c:when>
        		<c:otherwise>
        			<form class= "paragraph" action="./cart" method="post">
                	<input type="hidden" name="id" value="${book.id}">
                	<input type="hidden" name="action" value="add">
                	<button type="submit" class= "button">Add</button>
            		</form>
            	</c:otherwise>
            </c:choose>
 
            <br>
            <form class="paragraph" action="./cart" method="post">
                <input type="hidden" name="id" value="${book.id}">
                <input type="hidden" name="action" value="remove">
                <button type="submit" class= "button">Remove</button>
            </form>
            </div>
        </c:forEach>
        <div class="center-button">
    	<form action="./bookstoreOrderList" method="POST" name="action" value="order">
    		<button type="submit" class="button">Order</button>
        </form>
    	</div>
    	</div>
	</c:when>
	<c:otherwise>
        <div style="text-align: center;">
            <p style="font-size: 18px; color: #555;">Empty cart.</p>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>