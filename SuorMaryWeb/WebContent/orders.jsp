<%@page import="it.beije.suormary.bookstore1.OrderItem"%>
<%@page import="it.beije.suormary.bookstore1.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>I miei ordini</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./ShopServlet">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./CartServlet">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./OrderServlet">Orders</a></li>
		<li style="display: inline;"><a href="./LogoutServlet">Logout</a></li>
	</ul>
	<%List<Order> lo = (List<Order>)session.getAttribute("orders");
	
	for(Order order: lo){%>
		<div style="border:1px solid; margin-bottom:20px; padding:10px; background-color: #C5F6FA">
			<div style="border:1px solid; margin-bottom:10px; padding:10px; background-color: #C3C5FF">
				<h3><b>Numero ordine: <%=order.getId()%></b><br/></h3>
				
				<p><b>Indirizzo di spedizione</b>: <%=order.getShippingAddress() %></p>
				<p><b>Stato</b>: 
				<%
					switch(order.getStatus()){
						case 'I':
							out.print("Inserito");
							break;
						case 'P':
							out.print("Pagato");
							break;
						case 'C':
							out.print("Annullato");
							break;
					}
				%></p>
				
			</div>
		
			<%for(OrderItem orderItem: order.getItems()){%>
				<h2><%=orderItem.getBook().getTitle()%></h2>
				<p><b>Prezzo unitario</b>: <%=orderItem.getPrice()%> euro<br/>
				<b>Quantità</b>: <%=orderItem.getQuantity()%>
				</p>
				<hr style="border-color: #E8C5FA"/><br/>
			<%}%>
			<p><b>TOTALE:</b> <%=order.getAmount()%> euro</p>
			<% if(order.getStatus() == 'I'){ %>
			<form method="POST" action="./OrderServlet">
				<input type="hidden" name="orderId" value="<%=order.getId() %>" />
				<input type="submit" name="updateOrder" value="Paga" style="margin-right: 20px; background-color: #C5FAC7; font-size: 20px"/>
				<input type="submit" name="updateOrder" value="Annulla" style="background-color: #FAC5C5; font-size: 20px"/>
			</form>
			<%} %>
		</div>
	<%}
	%>

</body>
</html>