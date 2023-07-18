<%@page import="it.beije.suormary.controller.bookstore1.Book"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>Shop</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./ShopServlet">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./CartServlet">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./OrderServlet"">Orders</a></li>
		<li style="display: inline;"><a href="./LogoutServlet">Logout</a></li>
	</ul>
	<hr/>
	<c:choose>
	<c:when test="${empty books}">NESSUNO</c:when>
	<c:otherwise>
		<c:forEach items="${books}" var="b">
			<div style="border: 1px solid; margin-bottom: 10px; padding: 5px; background-color: #C5F6FA">
				<h2>${b.title}</h2>
				<h3>di ${b.author.name} ${b.author.surname}</h3>
				<p>
				<c:choose>
				<c:when test="${empty b.description}"> </c:when>
				<c:otherwise> ${b.description}</c:otherwise>
				</c:choose>	
				</p>
				<p>${b.editor}</p>
				<form method="POST" action="./shop">
					<input type="hidden" value="${b.id}" name="bookId" />
					<select name="quantity" >
					<c: forEach items="$"></c:>
						<%
						for(int i=1; i<=b.getQuantity(); i++){
						%>
							<option value="<%=i %>"><%=i %></option>
						<%} %>
					</select>
					<input type="submit" name="addBook" value="Aggiungi" />
				</form>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
	<%
	List<Book> books = (List<Book>)session.getAttribute("books"); 
	
	for(Book b : books){
	%>
	
	<%} 
	session.removeAttribute("books");
	%>
</body>
</html>