<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.User"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Order"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.OrderItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info user</title>
</head>
<body>

<%User user = (User) session.getAttribute("user");%>

Name : <%user.getName();%> <br/>
Surname : <%user.getSurname();%> <br/>
Email : <%user.getEmail();%> <br/>

<form action="/homeservlet" method="GET">
<input type="button" onclick="<% session.removeAttribute("user");%>" value="Log out"> 
</form>
<br/><br/>

<%List<Order> orders = (ArrayList<Orders>) session.getAtrtibute("orders");%>

<div style="width:100%">
<h3 style="text-align: center">ORDERS</h3>
	<table border="1">
	    <!-- Intestazione della tabella -->
	    <thead>
	        <tr>
	            <th>Id</th>        
	            <th>Date</th>
	            <th>Status</th>
	            <th>Amount</th>
	            <th>Shipping address</th>
	        </tr>
	        
	    </thead>
	    <!-- Corpo della tabella -->
	    <!-- CICLO -->
	    <tbody>
<% if (orders == null) {%>
	
	<tr><td style="color:red">No order</td></tr>
	<%
	}else {%>
	
		<%for (Order order : orders) {
			%>
			<tr>
	            <td><%=order.getId() %></td>            
	            <td><%=order.getDate()%></td>
	            <td><%=order.getSatus() %></td>
	            <td><%=order.getAmount%></td>
	            <td><%=order.getShippingAddress()%></td>
		    </tr>
		<%	}	
	}%>	</tbody>
	</table>

</div>
	
</body>
</html>