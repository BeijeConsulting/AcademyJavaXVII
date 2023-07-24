<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.model.User"%>
<%@page import="it.beije.suormary.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.suormary.model.Author"%>
<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="resources/style.css">
<style>
   h1 {
            color: #333;
        }

        form {
            margin-bottom: 10px;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #f9f9f9;
        }

        .card h3 {
            margin: 0;
            color: #555;
        }

        .card .buttons {
            margin-top: 10px;
        }

        .card .buttons input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-decoration: none;
            cursor: pointer;
            margin-right: 5px;
        }

        hr {
            margin-top: 20px;
            margin-bottom: 20px;
            border: 0;
            border-top: 1px solid #ccc;
        }
        .desc{
        font-size : 16px;
        font-weight: bold;
        }
        .button{
			    background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-decoration: none;
            cursor: pointer;
            margin-right: 5px;
             display: inline-block;
    margin-right: 10px;
			
		}
		.flex{
			display: flex;
		}
		.ord{
			color:green;
			font-weight: bold;
		}
        
</style>
<title>Ordine</title>
</head>
<body>
<%@ include file="header.jsp" %>
   <c:if test="${not empty ErrorQuantity}"> 
    	<p style="color:red">${ErrorQuantity}</p>
	</c:if>

<c:forEach items="${books}" var="book">
<div class="card">
	  <h5>Quantità disponibile : ${book.quantity}</h5>
            <h3>Titolo :  ${book.title} </h3>

            <c:if test="${not empty book.description}"> 
    			<span class="desc">Descrizione : </span>
    			<span>${book.description}</span>
			</c:if>
            <div class="buttons">
   <form action="addBookToOrder" action="GET">
   <input type="hidden" name="bookOrderId" value="${book.id}" />
    <label>Inserisci quantità : </label>
   <input type="number" name="quantity"/>
   <input type="submit" value="Aggiungi all`ordine" />
   </form>
            </div>
        </div>
 </c:forEach>

  <form action="recapOrder" action="GET">
   <input type="submit" value="Recap Ordine" class="button"/>
   </form>

</body>
</html>