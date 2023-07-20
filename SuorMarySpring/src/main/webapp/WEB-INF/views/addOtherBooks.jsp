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
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
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
           <form action="quantityBookModOrder" method="get">
   <input type="hidden" name="bookId" value= "${book.id}" />
   <label>Inserisci quantità : </label>
   <input type="number" name="quantity" />
   <input type="submit" value="inserisci" />
   </form>
   <form action="addBookToModOrder" action="GET">
   <input type="hidden" name="bookOrderId" value="${book.id}" />
   <input type="hidden" name="quantity" value = "<%= session.getAttribute("quantity") %>" />
   <input type="submit" value="Aggiungi all`ordine" />
   </form>
            </div>
        </div>
 </c:forEach>
  <form action="recapOrder" action="GET">
   <input type="submit" value="Salva modifica" class="button" /> 
   </form>

</body>
</html>