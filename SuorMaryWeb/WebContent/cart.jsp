<%@page import="java.util.Map"%>
<%@page import="it.beije.suormary.bookstore.entities.Book"%>
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
<title>Cart</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./ShopServlet">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./CartServlet">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./OrderServlet">Orders</a></li>
		<li style="display: inline;"><a href="./LogoutServlet">Logout</a></li>
	</ul>
	<hr/>
	<%
	Map<Book,Integer> books = (Map<Book,Integer>)session.getAttribute("books"); 
	
	for (Map.Entry<Book, Integer> entry : books.entrySet()) {
	%>
	<div style="border: 1px solid; margin-bottom: 10px; padding: 5px; background-color: #C5F6FA">
		<h2><%=entry.getKey().getTitle()%></h2>
		<h3>di <%=entry.getKey().getAuthor().getName()%> <%=entry.getKey().getAuthor().getSurname()%></h3>
		<p><%=(entry.getKey().getDescription() != null)? entry.getKey().getDescription() : ""%></p>
		<p><%=entry.getKey().getEditor() %></p>
		<p>Qty: <%=entry.getValue() %></p>
		<form method="POST" action="./CartServlet">
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
	<br/>
	<hr/>
	<br/>
	<div style="margin-bottom: 20px; margin-top: 10px; padding: 5px;">
		<form method="POST" action="./OrderServlet">
			<label>Indirizzo di consegna: </label>
			<input type="text" name="address" style="width: 500px;" /> <br/><br/>
			<input type="submit" name="updateOrder" value="Invia" style="width: 300px; font-size: 20px;" />
		</form>
	</div>
</body>
</html>