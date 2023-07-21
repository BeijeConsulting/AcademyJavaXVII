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
	<h1>LISTA LIBRI</h1>
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
					            <th>Edit</th>
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
						<br>
						<br>
				</c:otherwise>
			</c:choose>
		</div>
 		<div style="width:33%; float:right">
		<h3 style="text-align: center">CART</h3>
			<c:if test="${empty user }"></c:if> 
				<c:choose>
				<c:when test="${empty basket}">CART EMPTY</c:when> 
				<c:otherwise>
					<table border="1">
						<thead>
					        <tr>
					        	<th>ID</th>
					            <th>Title</th>      
					            <th>Author ID</th>
					            <th>Price</th>
					            <th>Quantity</th>
					        </tr>
					        
					    </thead>
					    <tbody>
							<c:forEach items="${basketJoinBook}" var="bjb">
								<tr>
									<td>${bjb.id}</td> 
						            <td>${bjb.title}</td> 
						            <td>${bjb.authorId}</td>            
						            <td>${bjb.price}</td>
						            <td>${bjb.quantity}</td>
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
				</c:otherwise>
			</c:choose>
		</div> 
	</div>
</body>
</html>
