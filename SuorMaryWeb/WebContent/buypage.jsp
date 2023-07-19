<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Order"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.OrderItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buy page</title>
</head>
<body>

<div style="justify-content: space-between; align-items: center">
<a href="infouserpage.jsp"><button style="border:solid; border-width: 1px; " >Profile</button></a>
<a href="./orderservlet"><button style="border:solid; border-width: 1px; " disabled>Buy books</button></a>
<a href="./homeservlet"><button style="border:solid; border-width: 1px; " >Catalogue</button></a>
</div>

<%
List<Book> books = (ArrayList<Book>) session.getAttribute("allBooks");
if (books == null) {
	%>
	<h2 style="color:red; text-align: center">LISTA LIBRI VUOTA</h2>
	<%
}else {
%>
	<div style="width:100%">
		<div style="width:63%; float:left">
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
			            <th>Add to basket</th>
			        </tr>
			        
			    </thead>
			    <!-- Corpo della tabella -->
			    <!-- CICLO -->
			    <tbody>
		<%for(int i=0;i<books.size();i++){
			%>
			<tr>
	            <td style="text-align: center"><%=books.get(i).getTitle() %></td>            
	            <td style="text-align: center"><%=books.get(i).getDescription()%></td>
	            <td style="text-align: center"><%=books.get(i).getAuthorId() %></td>
	            <td style="text-align: center"><%=books.get(i).getEditor()%></td>
	            <td style="text-align: center"><%=books.get(i).getPrice()%></td>
	            <td style="text-align: center"><%=books.get(i).getQuantity()%></td>
	            <%if (books.get(i).getQuantity() > 0) {%>
	            <td style="text-align: center"><a href="./orderservlet?indexBook=<%=books.get(i).getId()%>" ><button>+</button></a></td>
	            <% }%>
	             <!--  <td><a href="updatebook.jsp?indexButton=<%=i%>" ><input type="button" name="indexButton" value="E"></a></td>-->
		    </tr>
		<%	}	
	}%>	</tbody>
	</table>
	</div>
<%
	
HashMap<Book, Integer> basket = (HashMap<Book, Integer>)(session.getAttribute("basket"));

//Order basket = (Order)(session.getAttribute("basket"));
//List<OrderItem> basketList = basket.getItems();
%>

<div style="width:33%; float:right">
	<h3 style="text-align: center">CART</h3>
	<table border="1">

<!-- QUESTO è IL NUOVO MA NON RIESCO A COMMENTARE IL VECCHIO!!!! -->
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
<%
if (basket.isEmpty()) {
	%>
	<tr><td style="color:red">LISTA CARRELLO VUOTA</td></tr>
	<%
}else {%>
		<%for (HashMap.Entry<Book, Integer> set : basket.entrySet()){
			%>
			<tr>
				<td><%=set.getKey().getId()%></td> 
	            <td><%=set.getKey().getTitle()%></td> 
	            <td><%=set.getKey().getAuthorId()%></td>            
	            <td><%=set.getKey().getPrice()%></td>
	            <td><%=set.getValue()%></td>
		    </tr>
		<%} %>
			<tr>
				<td></td> 
		        <td></td> 
		        <td></td>            
		        <td><%=session.getAttribute("basketAmount")%></td>
	        </tr>
<%}%>

	</tbody>
	</table>
	<!-- somma prezzi -->
	<form action="payment.jsp" method="POST">
	<input type="submit" value="Buy">
	</form>
	
	</div>
</div>
	    <!-- Intestazione della tabella -->
<%-- 	    <thead>
	        <tr>
	            <th>Title</th>        
	            <th>Description</th>
	            <th>Author ID</th>
	            <th>Editor</th>
	            <th>Price</th>
	            <th>Quantity</th>
	        </tr>
	        
	    </thead>
	    <tbody>
	    
<%
if (basket == null) {
	%>
	<tr><td style="color:red">LISTA CARRELLO VUOTA</td></tr>
	<%
}else {%>
		<%for(int i=0;i<basketList.size();i++){
			%>
			<tr>
	            <td><%=basketList.get(i).getBookId() %></td>            
	            <td><%=basketList.get(i).getPrice()%></td>
	            <td ><%=basketList.get(i).getQuantity()%></td>
		    </tr>
		<%}
			
}%>
 --%>
</body>
</html>