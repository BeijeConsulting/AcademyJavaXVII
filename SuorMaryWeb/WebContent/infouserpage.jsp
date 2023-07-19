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

<div style="justify-content: space-between; align-items: center">
<a href="infouserpage.jsp"><button style="border:solid; border-width: 1px; " disabled>Profile</button></a>
<a href="./orderservlet"><button style="border:solid; border-width: 1px; " >Buy books</button></a>
<a href="./listservlet"><button style="border:solid; border-width: 1px; " >Catalogue</button></a>
</div>

<br/><br/>

<div>
<%User user = (User) session.getAttribute("user");%>

Name : <%=user.getName()%><br/><br/>
Surname : <%=user.getSurname()%><br/><br/>
Email : <%=user.getEmail()%><br/><br/>

 <form action="./homeservlet" method="GET">
<input type="submit" value="Log out"> 
</form>
</div>

<br/><br/>

<%List<Order> orders = (ArrayList<Order>) session.getAttribute("listOrders");%>

<div style="width:100%">
<h3 style="text-align: left">ORDERS</h3>
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
<% if (orders.isEmpty()) {%>
	
	<tr><td style="color:red">No order</td></tr>
	<%
	}else {%>
	
		<%for (Order order : orders) {
			%>
			<tr>
	            <td><%=order.getId() %></td>            
	            <td><%=order.getDate()%></td>
	            <td><%=order.getStatus() %></td>
	            <td><%=order.getAmount()%></td>
	            <td><%=order.getShippingAddress()%></td>
		    </tr>
		<%	}	
	}%>	</tbody>
	</table>

</div>
	
</body>
</html>