<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
<link rel="stylesheet" type="text/css" href="style.css">
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
</head>
<body>
<%@ include file="header.jsp" %>

<h1> Benvenuto&nbsp;${email}!!</h1>&nbsp;

<c:if test="${not empty deleteOrder}"><h2 style="color:green;">${deleteOrder}</h2> </c:if>
<c:if test="${not empty ordinePagato}"><h2 style="color:green;">${ordinePagato}</h2> </c:if>


<div style="display: flex; flex-direction: row;">
<form action="my_orders" method ="get">
	<input type="submit" value="I miei ordini" class="button">
</form>
<form action="createBook" method ="GET">
<input type="submit" value="aggiungi un libro" class="button" >
</form>

<form action="listAuthors" method ="GET">
<input type="submit" value="lista Autori" class="button">
</form>

<form action="createOrder" method ="GET">
<input type="submit" value="crea un ordine" class="button">
</form>
</div>
<h2>Libri disponibili:</h2><br/>

<c:choose>
	<c:when test="${empty books}"><h2>NESSUN LIBRO DISPONIBILE</h2></c:when>
	<c:otherwise>
		<c:forEach items="${books}" var="book">
		<div class="card">
			<h3 style="margin: 0;color: #555;">Titolo:&nbsp;${book.title}</h3>Disponibilità:&nbsp;${book.quantity}<br/>
			<h4>Descrizione:&nbsp;${book.description}</h4>
			<div style="display: flex; flex-direction: row;">
			 <form action="updateBook" method="GET" style="margin-bottom: 10px;">
                    <input type="hidden" name="id" value="${book.id}" />
                    <input type="submit" value="Modifica libro" class="button"/>
                </form>
                <form action="deleteBook" method="GET" style="margin-bottom: 10px;">
                    <input type="hidden" name="id" value="${book.id}" />
                    <input type="submit" value="Elimina libro" class="button" />
                </form>
                </div>
            </div>    
		</c:forEach>
	</c:otherwise>
</c:choose>

</body>
</html>