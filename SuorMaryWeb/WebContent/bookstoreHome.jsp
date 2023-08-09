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

        .welcome_container {
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

        .button_container {
            display: flex;
        	justify-content: flex-end; 
        	gap: 10px;

            
        }

        .button_container button {
            background-color: #04AA6D;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            
            
        }
        .button {
        	background-color: #2c5e29; 
        	color: white; 
        	border: none; 
        	padding: 8px 16px; 
        	border-radius: 4px; 
        	cursor: pointer;
        	
        }
        .book {
        	width: 300px; 
        	border: 1px solid #ccc; 
        	padding: 10px; 
        	background-color: #f9f9f9;
        	border-radius: 14px;
        }
        .paragraph {
        	text-align: center;
        }
        .title {
        	text-align: center; 
        	color: #333;
        }
        .div {
			display: flex; 
			flex-wrap: wrap; 
			gap: 20px;
		}

    </style>
</head>
<body>

<div class="container">
    <div class="welcome_container">
        <h1>Welcome to bookstore!</h1>
        <div class="button_container">
            <form action="./bookstoreLogin" method="GET">
                <button type="submit">login</button>
            </form>
           
            <form action="./bookstoreRegistration" method="GET">
                <button type="submit">Register</button>
            </form>
        </div>
    </div>

    <% List<Book> books = (List<Book>) request.getAttribute("books");%>
    <% List<Author> authors = (List<Author>) request.getAttribute("authors");%> 
    <div style = "margin-bottom: 50px; margin-top: 20px;">
    	<form style="text-align: center" action="./bookstoreInsertABook" method="get">
           <button type="submit" class = "button">Add a book</button>
        </form>
    </div>

	<% if (books != null && !books.isEmpty()) { %>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <% for(int i = 0; i < books.size(); i ++) { %>
             <div class = "book">
                <h2 class = "title"><%= books.get(i).getTitle() %></h2>
                <hr>
                <p class = "paragraph"><strong >Author :</strong> <%= authors.get(i).getName() %>  <%= authors.get(i).getSurname() %></p>
                <p class = "paragraph"><strong>Description:</strong> <%= books.get(i).getDescription() %></p>
                <form class = "paragraph" action="./bookstoreBookDetails" method="get">
                    <input type="hidden" name="id" value="<%= books.get(i).getId() %>">
                    <button type="submit" class = "button">View Details</button>
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