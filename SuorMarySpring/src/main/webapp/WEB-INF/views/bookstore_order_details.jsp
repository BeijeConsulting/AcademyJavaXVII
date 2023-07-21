<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="it.beije.suormary.bookstore2.model.Order" %>
<%@ page import="it.beije.suormary.bookstore2.model.OrderItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order details</title>
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
		.order_container {
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
    </style>
</head>
<body>
<div>
    <c:set var="order" value="${order}" />
    <c:set var="orderItems" value="${order.items}" />

    <c:if test="${not empty orderItems}">
        <h2 class="title">Books in order ${order.id}</h2>
        <p class="title"><strong>amount</strong> ${order.amount}</p>
        <p class="title"><strong>Order creation date :</strong> ${order.date.format(DateTimeFormatter.ofPattern('dd/MM/yyyy'))}</p>
        <div class="container">
            <c:forEach items="${orderItems}" var="orderItem" varStatus="loop">
                <div class="order_container">
                    <p class="paragraph"><strong>Book title :</strong> ${books[loop.index].title}</p>
                    <p class="paragraph"><strong>Book editor :</strong> ${books[loop.index].editor}</p>
                    <p class="paragraph"><strong>Price :</strong> ${orderItem.price} $</p>
                    <p class="paragraph"><strong>Quantity :</strong> ${orderItem.quantity}</p>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty orderItems}">
        <div class="paragraph">
            <p class="title">No order items found.</p>
        </div>
    </c:if>
    </div>
</body>
</html>

