<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.bookstore3.Order"%>
     <%@page import="it.beije.suormary.bookstore3.OrderItem"%>
     <%@page import="it.beije.suormary.bookstore3.Book"%>
     <%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  Order order = (Order) session.getAttribute("order");
%>
<form action="addOrderItems" action="get">
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
	   <input type="submit" value="rimuovi dall`ordine" />
	   </form>
	   <%
   }
	   %>
	      <form action="saveOrder" method="get">
	   <input type="submit" value="salva ordine" />
	   </form>
   


</body>
</html>