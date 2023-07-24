<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="it.beije.suormary.bookstore2.model.Order"%>
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
    </style>
</head>
<body>

       
 <div class="button_home">
	<form action="./bookstore_welcome" method="GET">
	   <button class = "button" type="submit">Home</button>
	</form>  
 </div>

    <c:set var="orders" value="${orders}" />

    <c:if test="${not empty orders}">
        <div class="container">
	
            <c:forEach items="${orders}" var="order">
                <div class="orders">
                    <h2 class="title"><strong>Order id:</strong> ${order.id}</h2>
                    <hr>
                    <p class="paragraph"><strong>Amount:</strong> ${order.amount} $</p>
                    <p class="paragraph"><strong>Status:</strong> ${order.status}</p>
                    <p class="paragraph"><strong>User_id:</strong> ${order.userId}</p>
                    <form class="paragraph" action="./bookstore_order_details" method="get">
                        <input type="hidden" name="id" value="${order.id}">
                        <button type="submit" class="button">View Details</button>
                    </form>
                    <br>
                    <c:if test="${order.status eq 'I'}">
                        <form class="paragraph" action="./bookstore_order_list" method="post">
                            <input type="hidden" name="id" value="${order.id}">
                            <button type="submit" class="button" name="action" value="cancel"
                                    onclick="return confirm('Are you sure you want to cancel this order?')">Cancel</button>
                        </form>
                    </c:if>
                    <c:if test="${order.status ne 'I'}">
                        <form class="paragraph">
                            <button type="submit" class="button" title="Order cannot be canceled as it is already processed." disabled>Cancel</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty orders}">
        <div class="title">
            <p>No order found.</p>
        </div>
    </c:if>
</body>
</html>