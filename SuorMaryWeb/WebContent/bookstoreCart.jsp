<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
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
        
        .center-button {
        text-align: center;
        width: 100%;
        }
        
        .books {
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
        .button {
        background-color: #2c5e29;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        }
        
        .button[disabled] {
          background-color: #ccc;
          color: #666;
          cursor: not-allowed;
        }
    </style>
</head>
<body>
<% List<Book> books = (List<Book>) request.getAttribute("books");%> 
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%> 
<% List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");%> 
<h1>Cart</h1>

	<% if (books != null && !books.isEmpty()) { %>
    <div class="container">
        <% for (int i = 0; i < books.size(); i ++) {
            Book book = books.get(i);
            Author author = authors.get(i);
            int quantity = quantities.get(i);
            int maxQuantity = book.getQuantity();
            boolean addButtonDisabled = quantity >= maxQuantity; 
            // Disabilita il pulsante "Add" se quantity >= maxQuantity
        %>
        <div class="books">
            <h2 class="title"><%= book.getTitle() %></h2>
            <hr>
            <p class= "paragraph"><strong>Author:</strong> <%= author.getName() %> <%= author.getSurname() %></p>
            <p class= "paragraph"><strong>Quantity:</strong> <%= quantity %></p>
            
            <% if (addButtonDisabled) {%>
            <form class="paragraph">
                	<button type="submit" class="button" 
                	title="Maximum amount of books reached for this book."
                	disabled>Add</button>
        		</form>
            <%} else{ %>
            <form class= "paragraph" action="./cart" method="post">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <input type="hidden" name="action" value="add">
                <button type="submit" class= "button">Add</button>
            </form>
            <%} %>
            
            <br>
            <form class="paragraph" action="./cart" method="post">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <input type="hidden" name="action" value="remove">
                <button type="submit" class= "button">Remove</button>
            </form>
        </div>
        <% } %>
        <div class="center-button">
    	<form action="./bookstoreOrderList" method="POST" name="action" value="order">
    		<button type="submit" class="button">Order</button>
        </form>
    	</div>
    </div>
<% } else { %>
        <div style="text-align: center;">
            <p style="font-size: 18px; color: #555;">Empty cart.</p>
        </div>
    <% } %>
</body>
</html>