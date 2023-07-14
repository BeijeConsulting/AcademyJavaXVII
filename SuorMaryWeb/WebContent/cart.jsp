<%@page import="java.util.Map"%>
<%@page import="it.beije.suormary.bookstore1.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./ShopServlet">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./CartServlet">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="#">Orders</a></li>
		<li style="display: inline;"><a href="#">Logout</a></li>
	</ul>
	<hr/>
	<%
	Map<Book,Integer> books = (Map<Book,Integer>)session.getAttribute("books"); 
	
	for (Map.Entry<Book, Integer> entry : books.entrySet()) {
	%>
	<div style="border: 1px solid; margin-bottom: 10px; padding: 5px;">
		<h2><%=entry.getKey().getTitle()%></h2>
		<h3>di <%=entry.getKey().getAuthor().getName()%> <%=entry.getKey().getAuthor().getSurname()%></h3>
		<p><%=entry.getKey().getDescription() %></p>
		<p><%=entry.getKey().getEditor() %></p>
		<p>Qty: <%=entry.getValue() %></p>
		<form method="POST" action="./ShopServlet">
			<input type="hidden" value="<%=entry.getKey().getId() %>" name="bookId" />
			<select name="quantity" >
				<%
				for(int i=1; i<=entry.getValue(); i++){
				%>
					<option value="<%=i %>"><%=i %></option>
				<%} %>
			</select>
			<input type="submit" name="removeBook" value="Rimuovi" />
		</form>
	</div>
	<%} 
	
	session.removeAttribute("books");%>
</body>
</html>