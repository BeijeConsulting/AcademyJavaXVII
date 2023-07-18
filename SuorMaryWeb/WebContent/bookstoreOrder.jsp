<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="it.beije.suormary.bookstore2.model.Order"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="it.beije.suormary.bookstore2.model.OrderItem"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
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
<% Order order = (Order) request.getAttribute("order");%> 
<% List<OrderItem> orderItems = order.getItems(); %> 
<% List<Book> books = (List<Book>) request.getAttribute("books");%>
<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");%>
<% String formattedDate = order.getDate().format(formatter); %>

              <% if (orderItems != null && !orderItems.isEmpty()) { %>
              <h2 class = "title">Books in order <%= order.getId()%> </h2>
              <p class = "title"><strong>amount</strong> <%= order.getAmount()%></p>
                     <div class="container">
                     <% for (int i = 0; i < orderItems.size(); i ++) {
                    	 OrderItem orderItem = orderItems.get(i);
                    	 Book b = books.get(i);
                    	 %>
					    <div class = "order_container">
					      <p class ="title"><strong>Order creation date : </strong> <%= formattedDate%></p>
					      <p class = "paragraph"><strong>Book title :</strong> <%= b.getTitle()%></p>
					      <p class = "paragraph"><strong>Book editor:</strong> <%= b.getEditor() %></p>
					      <p class = "paragraph"><strong>Price :</strong> <%= orderItem.getPrice() %> $ </p>
					      <p class = "paragraph"><strong>Quantity :</strong> <%= orderItem.getQuantity() %></p>
					    </div>
					  <% } %>
					</div>
						               
                <% } else { %>
                    <div class = "paragraph">
                        <p class = "title">No order items found.</p>
                    </div>
                <% } %>

</div>

</body>
</html>