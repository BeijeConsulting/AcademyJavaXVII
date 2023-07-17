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
</head>
<body>
<% List<Book> books = (List<Book>) request.getAttribute("books");%> 
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%> 
<% List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");%> 
<h1>Cart</h1>
    

	<% if (books != null && !books.isEmpty()) { %>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <% for (int i = 0; i < books.size(); i ++) {
            Book book = books.get(i);
            Author author = authors.get(i);
            int quantity = quantities.get(i);
        %>
        <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
            <h2 style="text-align: center; color: #333;"><%= book.getTitle() %></h2>
            <hr>
            <p style="text-align: center"><strong>Author:</strong> <%= author.getName() %> <%= author.getSurname() %></p>
            <p style="text-align: center"><strong>Quantity:</strong> <%= quantity %></p>
            <form style="text-align: center" action="./cart" method="post">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <input type="hidden" name="action" value="add">
                <button type="submit">Add</button>
            </form>
            <form style="text-align: center" action="./cart" method="post">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <input type="hidden" name="action" value="remove">
                <button type="submit">Remove</button>
            </form>
        </div>
        <% } %>
    </div>
<% } else { %>
        <div style="text-align: center;">
            <p style="font-size: 18px; color: #555;">Empty cart.</p>
        </div>
    <% } %>
</body>
</html>