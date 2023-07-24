<%@page import="it.beije.suormary.bookstore1.Book"%>
<%@page import="java.util.List"%>
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
	<%
	List<Book> books = (List<Book>)session.getAttribute("books"); 
	
	for(Book b : books){
	%>
	<div style="border: 1px solid; margin-bottom: 10px; padding: 5px; background-color: #C5F6FA">
		<h2><%=b.getTitle()%></h2>
		<h3>di <%=b.getAuthor().getName()%> <%=b.getAuthor().getSurname()%></h3>
		<p><%=(b.getDescription() != null)? b.getDescription() : ""%></p>
		<p><%=b.getEditor() %></p>
		<form method="POST" action="./ShopServlet">
			<input type="hidden" value="<%=b.getId() %>" name="bookId" />
			<select name="quantity" >
				<%
				for(int i=1; i<=b.getQuantity(); i++){
				%>
					<option value="<%=i %>"><%=i %></option>
				<%} %>
			</select>
			<input type="submit" name="addBook" value="Aggiungi" />
		</form>
	</div>
	<%} 
	session.removeAttribute("books");
	%>
</body>
</html>