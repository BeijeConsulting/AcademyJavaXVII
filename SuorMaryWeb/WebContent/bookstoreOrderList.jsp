<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="it.beije.suormary.bookstore2.model.Order"%>
<%@page import="it.beije.suormary.bookstore2.model.OrderItem"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your ordes</title>
</head>
<body>

<% List<Order> orders = (List<Order>) request.getAttribute("orders");%> 

<% if (orders != null && !orders.isEmpty()) { %>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <% for (Order order : orders) { %>
             <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                <h2 style="text-align: center; color: #333;"><strong>Order id:</strong><%= order.getId() %></h2>
                <hr>
                <p style="text-align: center"><strong>Amount:</strong> <%= order.getAmount()%></p>
                <p style="text-align: center"><strong>Status:</strong> <%= order.getStatus() %></p>
                <p style="text-align: center"><strong>User_id:</strong> <%= order.getUserId() %></p>
                
               <% List<OrderItem> orderItems = order.getItems();%> 
              <% if (orderItems != null && !orderItems.isEmpty()) { %>
                     <div style = "background-color: #f9f9f9; padding: 20px; border-radius: 5px;">
					  <h2>Books</h2>
					
					  <% for (OrderItem orderItem : orderItems) { %>
					    <!-- Book List -->
					    <div Style= "margin-top: 10px;padding: 10px;background-color: #fff;border: 1px solid #ccc;border-radius: 5px;">
					      <p><strong>Book_id:</strong> <%= orderItem.getBookId()%></p>
					      <p><strong>Price:</strong> <%= orderItem.getPrice() %></p>
					      <p><strong>Quantity:</strong> <%= orderItem.getQuantity() %></p>
					    </div>
					  <% } %>
					</div>
						               
                <% } else { %>
                    <div style="text-align: center;">
                        <p style="font-size: 18px; color: #555;">No order items found.</p>
                    </div>
                <% } %>
              
            </div>
        <% } %>
    </div>
<% } else { %>
    <div style="text-align: center;">
        <p style="font-size: 18px; color: #555;">No order found.</p>
    </div>
<% } %>

</body>
</html>