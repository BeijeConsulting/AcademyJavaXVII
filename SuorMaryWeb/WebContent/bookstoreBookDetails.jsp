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

    <div style="display: flex; flex-wrap: wrap; gap: 20px;">

            <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                <p><strong>Title:</strong> <%= book.getTitle() %></p>
                <p><strong>Author:</strong> <%= book.getAuthorId() %></p>
              	<p><strong>Description:</strong> <%= book.getDescription() %></p>
              	<p><strong>Editor:</strong> <%= book.getEditor() %></p>
              	<p><strong>Price:</strong> <%= book.getPrice() %></p>
              	<p><strong>Quantity:</strong> <%= book.getQuantity() %></p>

            </div>

    </div>
</body>
</html>