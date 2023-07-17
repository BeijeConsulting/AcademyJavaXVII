<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="it.beije.suormary.bookstore2.model.Order"%>
<%@page import="it.beije.suormary.bookstore2.model.OrderItem"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your orders</title>
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
        .orders {
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
    </style>

</head>
<body>

<% List<Order> orders = (List<Order>) request.getAttribute("orders");%> 

<% if (orders != null && !orders.isEmpty()) { %>
    <div class="container">
        <% for (Order order : orders) { %>
             <div class= "orders">
             
                <h2 class="title"><strong>Order id:</strong><%= order.getId() %></h2>
                <hr>
                <p class= "paragraph"><strong>Amount:</strong> <%= order.getAmount()%> $</p>
                <p class= "paragraph"><strong>Status:</strong> <%= order.getStatus() %></p>
                <p class= "paragraph"><strong>User_id:</strong> <%= order.getUserId() %></p>
            	<form class= "paragraph" action="./bookstoreOrderDetails" method="get">
                    <input type="hidden" name="id" value="<%= order.getId() %>">
                    <button type="submit" class= "button">View Details</button>
                </form>
            
            </div>
        <% } %>
    </div>
<% } else { %>
    <div class="title">
        <p>No order found.</p>
    </div>
<% } %>

</body>
</html>