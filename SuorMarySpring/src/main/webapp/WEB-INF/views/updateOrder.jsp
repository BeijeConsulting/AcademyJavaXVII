<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.model.Order"%>
     <%@page import="it.beije.suormary.model.OrderItem"%>
     <%@page import="it.beije.suormary.model.Book"%>
     <%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Order</title>
</head>
<body>
<%
  Order order = (Order) session.getAttribute("order");
%>
<form action="addOtherBooks" action="get">
<input type="submit" value="Aggiungi altri libri" />
</form>
<%
   for(OrderItem orderItem : order.getItems()){
	   Book book = BookStoreUtility.getBookById(orderItem.getBookId());
	   %>
	   <h4>Quantity : <%= orderItem.getQuantity() %></h4>
	   <h3><%= book.getTitle() %></h3>
	   <form action="deleteOrderItem" method="get">
	   <input type="hidden" name="orderItemId" value="<%= orderItem.getId() %>" />
	   <input type="submit" value="rimuovi dall`ordine" /> <br>
	   </form>
	   <%
   }
	   %>
	      <form action="saveOrder" method="get">
	   <input type="submit" value="salva ordine" />
	   </form>
   


</body>
</html>