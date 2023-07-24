<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stock page</title>
</head>
<body>
<div style="display: flex;">
	<c:if test="${empty user}">
		<div style="flex: 1;">
			<form:form action="loginbutton" method="GET">
				<input type="submit" value="Log in">
			</form:form>
		</div>
	</c:if>
	<%-- <c:otherwise> --%>
	<c:if test="${not empty user}">
		<div style="flex: 1;">
			<form:form action="infouser" method="GET">
				<input type="submit" value="Profile">
			</form:form> 
		</div>
		
		<div style="flex: 1;">
			<form:form action="booklist" method="GET">
				<input type="submit" value="Buy book">
			</form:form>
		</div> 
		
		<div style="flex: 1;">
			<form:form action="stock" method="GET">
				<input type="submit" value="Stock" disabled>
			</form:form> 
		</div>
	<%-- </c:otherwise> --%>
	</c:if>
	
</div>
	<div style="width:100%">
		<div style="width:58%; float:left">
			<h3 style="text-align: center">BOOKS</h3>
			<c:choose>
				<c:when test="${empty booklist}"><p style="text-align: center">NO BOOKS</p></c:when>
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
									<td><form:form action="editbook" method="POST">
										<input type="hidden" name="bookId" value="${book.id}"/>
									    <input type="submit" value="E"/>
									</form:form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<form:form action="addbook" method="GET">
				    <input type="submit" value="Add Book"/>
				</form:form>
				</c:otherwise>
			</c:choose>
		</div>
		 <div style="width:33%; float:right">
			<h3 style="text-align: center">AUTHORS</h3>
					<c:choose>
					<c:when test="${empty authorlist}"><p style="text-align: center">NO AUTHORS</p></c:when> 
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
								<c:forEach items="${authorlist}" var="author">
									<tr>
										<td>${author.id}</td> 
							            <td>${author.name}</td> 
							            <td>${author.surname}</td>            
							            <td>${author.description}</td>
							            <td><form:form action="editauthor" method="POST">
											<input type="hidden" name="bookId" value="${author.id}"/>
										    <input type="submit" value="E"/>
										</form:form></td>
								    </tr>
						        </c:forEach> 
					        </tbody>			        
						</table>
						<form:form action="addauthor" method="GET">
				    		<input type="submit" value="Add Author"/>
						</form:form>
					</c:otherwise>
				</c:choose>
			</div>  
	</div> 
	<br/>
	<br/>
</body>
</html>