<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>book list</title>
</head>
<body>
<div style="display: flex;">
<!-- METTERE VICINI NON A CAPO -->
<div style="flex: 1;">
<form:form action="infouser" method="GET">
<input type="submit" value="Profile">
</form:form> 
</div>

<div style="flex: 1;">
<form:form action="booklist" method="GET">
<input type="submit" value="Buy book" disabled>
</form:form> 
</div>

<div style="flex: 1;">
<form:form action="stock" method="GET">
<input type="submit" value="Stock">
</form:form> 
</div>
</div>

	<div style="width:100%">
		<div style="width:58%; float:left">
		<h3 style="text-align: center">BOOKS</h3>
			<c:choose>
				<c:when test="${empty booklist}">NO BOOKS</c:when>
				<c:otherwise>
					<table border="1">
					    <!-- Intestazione della tabella -->
					    <thead>
					        <tr>
					        	<th>ID</th>
					            <th>Title</th>        
					            <th>Description</th>
					            <th>Author ID</th>
					            <th>Editor</th>
					            <th>Price</th>
					            <th>Quantity</th>
					            <th>Add to basket</th>
					        </tr>
					        
					    </thead>
					    <tbody>
							<c:forEach items="${booklist}" var="book">
								<tr>
								 	<td>${book.id}</td>  
						            <td>${book.title}</td>            
						            <td>${book.description}</td>
						            <td>${book.authorId}</td>
						            <td>${book.editor}</td>
						            <td>${book.price}</td>
						            <td>${book.quantity}</td>
									<td><form:form action="addtobasket" method="POST">
										<input type="hidden" name="bookId" value="${book.id}"/>
									    <input type="submit" value="+"/>
									</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
				</c:otherwise>
			</c:choose>
		</div>
 		<div style="width:33%; float:right">
		<h3 style="text-align: center">CART</h3>
			<c:if test="${empty user }"></c:if> 
				<c:choose>
				<c:when test="${empty basket}"><p style="text-align: center">CART EMPTY</p></c:when> 
				<c:otherwise>
					<table border="1">
						<thead>
					        <tr>
					        	<th>ID</th>
					            <th>Title</th>      
					            <th>Author ID</th>
					            <th>Price</th>
					            <th>Quantity</th>
					            <th>Remove from basket</th>
					        </tr>
					        
					    </thead>
					    <tbody>
							<c:forEach items="${basket}" var="entry">
								<tr>
									<td>${entry.key.id}</td> 
						            <td>${entry.key.title}</td> 
						            <td>${entry.key.authorId}</td>            
						            <td>${entry.key.price}</td>
						            <td>${entry.value}</td>
						            <td><form:form action="removefrombasket" method="POST">
										<input type="hidden" name="bookId" value="${entry.key.id}"/>
									    <input type="submit" value="-"/>
									</form:form></td>
							    </tr>
							   
					        </c:forEach> 
					        <tr>
								<td></td> 
						        <td></td> 
						        <td></td>            
						        <td><input type="text" style="color: red" name="sum" value="${sum}" readonly/></td>
				        	</tr>
				        </tbody>			        
					</table>
					<form:form action="payment" method="GET">
					    <input type="submit" value="Buy"/>
					</form:form>
				</c:otherwise>
			</c:choose>
		</div> 
	</div>
</body>
</html>
