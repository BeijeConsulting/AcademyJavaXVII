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
<title>Update Order</title>
</head>
<body>
<form action="addOtherBooks" action="get">
<input type="submit" value="Aggiungi altri libri" />
</form>
<c:forEach items="${order.items}" var="orderItem">
    <c:set var="book" value="${BookStoreUtility.getBookById(orderItem.bookId)}" />
    ${book.title} - Quantità: ${orderItem.quantity} <br/>
    <h4>Quantity : ${orderItem.quantity}</h4>
	   <h3>${book.title}</h3>
	   <form action="deleteOrderItem" method="get">
	   <input type="hidden" name="orderItemId" value="${orderItem.id}" />
	   <input type="hidden" name="orderId" value="${order.id}" />
	   <input type="submit" value="rimuovi dall`ordine" /> <br>
	   </form>
</c:forEach>

	      <form action="saveOrder" method="get">
	   <input type="submit" value="salva ordine" />
	   </form>
   


</body>
</html>