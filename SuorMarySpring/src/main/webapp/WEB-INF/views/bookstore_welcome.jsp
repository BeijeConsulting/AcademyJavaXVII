<%@page import="it.beije.suormary.bookstore2.model.User"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
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
        .button_favorite {
        	background-color: #3d3f42; 
        	color: white; 
        	border: none; 
        	padding: 3px 3px; 
        	border-radius: 4px; 
        	cursor: pointer;
        	
        }
        .button_unfavorite {
          background-color: #ccc;
          color: #666;
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
<jsp:useBean id="user" class="it.beije.suormary.bookstore2.model.User" scope="session"></jsp:useBean>



<div class="container">
    <div class="welcome_container">
        <h1>Welcome, ${user.name} ${user.surname}!</h1>
        <div class="button_container">
            <form action="./bookstoreOrderList" method="GET">
                <button type="submit">View Order List</button>
            </form>
            <form action="./cart" >
                <button type="submit">Cart</button>
            </form>
            <form action="./logout" method="GET">
                <button type="submit">Logout</button>
            </form>
            <form action="./bookstore_profile" method="GET">
                <button type="submit">Profile</button>
            </form>
        </div>
    </div>
    
    <div style = "margin-bottom: 50px; margin-top: 20px;">
    	<form style="text-align: center" action="./bookstoreInsertABook" method="get">
           <button type="submit" class = "button">Add a book</button>
           
        </form>
    </div>
    
    <c:choose>
    <c:when test="${not empty books}">
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
 
    	<c:set var="books" value="${books}"/>
        <c:forEach items="${books}" var="book" varStatus="loop">
    <div class="book">
    	<div style="display: flex; justify-content: end">
        <form action="./bookstore_favorites" method="post">
           <input type="hidden" name="id" value="${book.id}">
           <input type="hidden" name="action" value="addBookToFav">
           <button class ="button_favorite"><i class="material-icons"> favorite</i></button>
        </form>
        </div>  
         <h2 class="title">${book.title}</h2>
        <hr>
        <p class="paragraph"><strong>Author :</strong> ${authors[loop.index].name} ${authors[loop.index].surname}</p>
        <p class="paragraph"><strong>Description:</strong> ${book.description}</p>
        <form class="paragraph" action="./bookstore_book_details" method="get">
            <input type="hidden" name="id" value="${book.id}">
            <button type="submit" class="button">View Details</button>
        </form>
    </div>
</c:forEach>
    </div>
    </c:when>
<c:otherwise>
    <div style="text-align: center;">
        <p style="font-size: 18px; color: #555;">No book found.</p>
    </div>
</c:otherwise>
</c:choose>
</div>
 
</body>
</html>