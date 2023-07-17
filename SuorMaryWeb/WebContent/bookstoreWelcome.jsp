<%@page import="it.beije.suormary.bookstore2.model.User"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome</title>
    <style>
        body {
            font-family: "Roboto", sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            height: 100vh;
            padding: 16px;
            border-radius: 14px;
        }

        .welcome-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 24px;
            color: #333;
            margin: 0;
        }

        .button-container {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .button-container button {
            background-color: #04AA6D;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<jsp:useBean id="user" class="it.beije.suormary.bookstore2.model.User" scope="session"></jsp:useBean>
<%
if (user == null) response.sendRedirect("bookstoreLogin.jsp");
%>

<div class="container">
    <div class="welcome-container">
        <h1>Welcome, <%= user.getName() %> <%= user.getSurname() %>!</h1>
        <div class="button-container">
            <form action="./bookstoreOrderList" method="GET">
                <button type="submit">View Order List</button>
            </form>
            <form action="./cart" >
                <button type="submit">Cart</button>
            </form>
            <form action="./logout" method="GET">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>

    <% List<Book> books = (List<Book>) request.getAttribute("books");%>
    <% List<Author> authors = (List<Author>) request.getAttribute("authors");%> 

	<% if (books != null && !books.isEmpty()) { %>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <% for(int i = 0; i < books.size(); i ++) { %>
             <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                <h2 style="text-align: center; color: #333;"><%= books.get(i).getTitle() %></h2>
                <hr>
                <p style="text-align: center"><strong >Author :</strong> <%= authors.get(i).getName() %>  <%= authors.get(i).getSurname() %></p>
                <p style="text-align: center"><strong>Description:</strong> <%= books.get(i).getDescription() %></p>
                <form style="text-align: center" action="./bookstoreBookDetails" method="get">
                    <input type="hidden" name="id" value="<%= books.get(i).getId() %>">
                    <button type="submit" style="background-color: #2c5e29; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer;">View Details</button>
                </form>
            </div>
        <% } %>
    </div>
<% } else { %>
        <div style="text-align: center;">
            <p style="font-size: 18px; color: #555;">No book found.</p>
        </div>
    <% } %>
</div>
</body>
</html>