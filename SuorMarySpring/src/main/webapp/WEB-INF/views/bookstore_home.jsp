<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
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
            <form action="./bookstore_login" method="GET">
                <button type="submit">login</button>
            </form>
           
            <form action="./bookstore_register" method="GET">
                <button type="submit">Register</button>
            </form>
        </div>
    </div>
  
    <c:choose>
    <c:when test="${not empty books}">
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
    	<c:set var="books" value="${books}"/>
        <c:forEach items="${books}" var="book" varStatus="loop">
    <div class="book">
        <h2 class="title">${book.title}</h2>
        <hr>
        <p class="paragraph"><strong>Author :</strong> ${authors[loop.index].name} ${authors[loop.index].surname}</p>
        <p class="paragraph"><strong>Description:</strong> ${book.description}</p>
        <form class="paragraph" action="./bookstoreBookDetails" method="get">
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