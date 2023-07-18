<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buy page</title>
</head>
<body>
<%
List<Book> books = (ArrayList<Book>) session.getAttribute("allBooks");
if (books == null) {
	%>
	<h2 style="color:red; text-align: center">LISTA LIBRI VUOTA</h2>
	<%
}else {
%>
	<div style="width:100%">
		<div style="width:62%; float:left">
			<h3 style="text-align: center">BOOKS</h3>
			<table border="1">
			    <!-- Intestazione della tabella -->
			    <thead>
			        <tr>
			            <th>Title</th>        
			            <th>Description</th>
			            <th>Author ID</th>
			            <th>Editor</th>
			            <th>Price</th>
			            <th>Quantity</th>
			        </tr>
			        
			    </thead>
			    <!-- Corpo della tabella -->
			    <!-- CICLO -->
			    <tbody>
		<%for(int i=0;i<books.size();i++){
			%>
			<tr>
	            <td><%=books.get(i).getTitle() %></td>            
	            <td><%=books.get(i).getDescription()%></td>
	            <td><%=books.get(i).getAuthorId() %></td>
	            <td><%=books.get(i).getEditor()%></td>
	            <td ><%=books.get(i).getPrice()%></td>
	            <td ><%=books.get(i).getQuantity()%></td>
	            <td><a href="./orderservlet?indexButton=<%=i%>" >+</a></td>
	             <!--  <td><a href="updatebook.jsp?indexButton=<%=i%>" ><input type="button" name="indexButton" value="E"></a></td>-->
		    </tr>
		<%	}	
	}%>	</tbody>
	</table>
	</div><%
List<Book> cart = (ArrayList<Book>)(session.getAttribute("cartBooks"));
%><div style="width:32%; float:right">
	<h3 style="text-align: center">CART</h3>
	<table border="1">
	    <!-- Intestazione della tabella -->
	    <thead>
	        <tr>
	            <th>Title</th>        
	            <th>Description</th>
	            <th>Author ID</th>
	            <th>Editor</th>
	            <th>Price</th>
	            <th>Quantity</th>
	        </tr>
	        
	    </thead>
	    <tbody><%
if (cart == null) {
	%>
	<tr><td style="color:red">LISTA CARRELLO VUOTA</td></tr>
	<%
}else {%>
		<%for(int i=0;i<cart.size();i++){
			%>
			<tr>
				<tr>
	            <td><%=books.get(i).getTitle() %></td>            
	            <td><%=books.get(i).getDescription()%></td>
	            <td><%=books.get(i).getAuthorId() %></td>
	            <td><%=books.get(i).getEditor()%></td>
	            <td ><%=books.get(i).getPrice()%></td>
	            <td ><%=books.get(i).getQuantity()%></td>
	            <td><a href="./orderservlet?indexButton=<%=i%>" >Buy</a></td>
		    </tr>
		<%}
			
	}%></tbody>
	</table>
	</div>
	</div>

</body>
</html>