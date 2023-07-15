<%@page import="it.beije.suormary.bookstore2.model.User"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Descrizione libro</title>
</head>
<body>

<% Book book = (Book) request.getAttribute("book");%> 

   <div style="display: flex; flex-wrap: wrap; gap: 20px;">

    <% if (book != null) { %>
         <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                <h2 style="text-align: center; color: #333;"><%= book.getTitle() %></h2>
                <hr>
            <p style="text-align: center"><strong >AuthorId:</strong> <%= book.getAuthorId() %></p>
            <p style="text-align: center"><strong>Description:</strong> <%= book.getDescription() %></p>
            <p style="text-align: center"><strong>Editor:</strong> <%= book.getEditor() %></p>
            <p style="text-align: center"><strong>Price:</strong> $<%= book.getPrice() %></p>
            <p style="text-align: center"><strong>Quantity:</strong> <%= book.getQuantity() %></p>
            <form style="text-align: center" action="./CartServlet" method="post">
                    <input type="hidden" name="id" value="<%= book.getId() %>">
             		<button type="submit" style="background-color: #2c5e29; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer;">Add to Cart</button>
            </form>
        </div>
    <% } else { %>
       <div style="text-align: center;">
        <p style="font-size: 18px; color: #555;">No book found.</p>
       </div>
    <% } %>

	</div>
</body>
</html>