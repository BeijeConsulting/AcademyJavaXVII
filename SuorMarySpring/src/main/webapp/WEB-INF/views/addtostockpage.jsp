<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add to stock</title>
</head>
<body>

	<c:when test="${empty user}">
	<form:form action="home" method="GET">
		<input type="submit" value="Log in">
		</form:form>
	</c:when>
	<c:otherwise>
		<form:form action="infouser" method="GET">
		<input type="submit" value="Profile">
		</form:form> 
		
		<form:form action="booklist" method="GET">
		<input type="submit" value="Buy book">
		</form:form> 
		
		<form:form action="stock" method="GET">
		<input type="submit" value="Stock" disabled>
		</form:form> 
	</c:otherwise>
	
	<div style="width:100%">
		<div style="width:58%; float:left">
			<h3 style="text-align: center">BOOKS</h3>
			<c:choose>
				<c:when test="${empty booklist}">NO BOOKS</c:when>
				<c:otherwise>
					<table border="1">
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
									<td><form:form action="editBook" method="POST">
										<input type="hidden" name="bookId" value="${book.id}"/>
									    <input type="submit" value="E"/>
									</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
				<form:form action="addBook" method="GET">
				    <input type="submit" value="Add Book"/>
				</form:form>
			</c:choose>
		</div>
		<div style="width:33%; float:right">
			<h3 style="text-align: center">AUTHORS</h3>
					<c:choose>
					<c:when test="${empty authorslist}">NO AUTHORS</c:when> 
					<c:otherwise>
						<table border="1">
							<thead>
						        <tr>
						        	<th>ID</th>
						            <th>Name</th>      
						            <th>Surname</th>
						            <th>Description</th>
						        </tr>	       
						    </thead>
						    <tbody>
								<c:forEach items="${authorslist}" var="author">
									<tr>
										<td>${author.id}</td> 
							            <td>${author.name}</td> 
							            <td>${author.surname}</td>            
							            <td>${author.description}</td>
							            <td><form:form action="editAuthor" method="POST">
											<input type="hidden" name="bookId" value="${author.id}"/>
										    <input type="submit" value="E"/>
										</form:form></td>
								    </tr>
						        </c:forEach> 
					        </tbody>			        
						</table>
					</c:otherwise>
					<form:form action="addAuthor" method="GET">
				    	<input type="submit" value="Add Author"/>
					</form:form>
				</c:choose>
			</div> 
	</div>
</body>
</html>