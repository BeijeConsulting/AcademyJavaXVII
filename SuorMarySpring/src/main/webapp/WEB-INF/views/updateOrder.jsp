<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.model.Order"%>
     <%@page import="it.beije.suormary.model.OrderItem"%>
     <%@page import="it.beije.suormary.model.Book"%>
     <%@page import="it.beije.suormary.controller.BookStoreUtility"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
   h1 {
            color: #333;
        }

        form {
            margin-bottom: 10px;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #f9f9f9;
        }

        .card h3 {
            margin: 0;
            color: #555;
        }

        .card .buttons {
            margin-top: 10px;
        }

        .card .buttons input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-decoration: none;
            cursor: pointer;
            margin-right: 5px;
        }

        hr {
            margin-top: 20px;
            margin-bottom: 20px;
            border: 0;
            border-top: 1px solid #ccc;
        }
        .desc{
        font-size : 16px;
        font-weight: bold;
        }
        .button{
			    background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-decoration: none;
            cursor: pointer;
            margin-right: 5px;
             display: inline-block;
    margin-right: 10px;
			
		}
		.flex{
			display: flex;
		}
		.ord{
			color:green;
			font-weight: bold;
		}
        
</style>
<title>Update Order</title>
</head>
<body>

<form action="addOtherBooks" action="get">
<input type="submit" value="Aggiungi altri libri" class="button" />
</form> 
<c:forEach items="${order.items}" var="orderItem">
<div class="card">
    <c:set var="book" value="${BookStoreUtility.getBookById(orderItem.bookId)}" />
    <h4>${book.title} - Quantità: ${orderItem.quantity} </h4>
	   <form action="deleteOrderItem" method="get">
	   <input type="hidden" name="orderItemId" value="${orderItem.id}" />
	   <input type="hidden" name="orderId" value="${order.id}" />
	   <input type="submit" value="rimuovi dall`ordine" class="button"/> <br>
	   </form>
	   </div>
</c:forEach>

</body>
</html>