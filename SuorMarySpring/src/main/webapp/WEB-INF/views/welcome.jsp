<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
<%@ include file="header.jsp" %>

<h1> WELCOME&nbsp;${email}!!</h1>&nbsp;

<%
  if(session.getAttribute("ordinePagato") != null){
	  %>  
	  <h3 style="color:green;font-weight:bold;"><%= session.getAttribute("ordinePagato") %></h3>
	  <%
	  session.removeAttribute("ordinePagato");
  }
	  %>
	  <%
  if(session.getAttribute("deleteOrder") != null){
	  %>  
	  <h3 style="color:green;font-weight:bold;"><%= session.getAttribute("deleteOrder") %></h3>
	  <%
	  session.removeAttribute("deleteOrder");
  }
	  %>

<form action="my_orders" method ="get">
	<input type="submit" value="I miei ordini" class="button">
</form>&nbsp;
<form action="createBook" method ="GET">
<input type="submit" value="aggiungi un libro" class="button">
</form>
&nbsp;
<form action="createOrder" method ="GET">
<input type="submit" value="crea un ordine" class="button">
</form>
&nbsp;
<h2>Libri disponibili:</h2><br/>

<c:choose>
	<c:when test="${empty books}"><h2>NESSUN LIBRO DISPONIBILE</h2></c:when>
	<c:otherwise>
		<c:forEach items="${books}" var="book">
			<h3>Titolo:&nbsp;${book.title}</h3>Disponibilit�:&nbsp;${book.quantity}<br/>
			<h4>Descrizione:&nbsp;${book.description}</h4>
			 <form action="updateBook" method="GET">
                    <input type="hidden" name="id" value="${book.id}" />
                    <input type="submit" value="Modifica libro" />
                </form>
                <form action="deleteBook" method="GET">
                    <input type="hidden" name="id" value="${book.id}" />
                    <input type="submit" value="Elimina libro" />
                </form>
		</c:forEach>
	</c:otherwise>
</c:choose>


</body>
</html>