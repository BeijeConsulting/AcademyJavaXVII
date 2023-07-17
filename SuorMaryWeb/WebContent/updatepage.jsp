<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>UPDATE BOOK</h1>


<% Book book = (Book) session.getAttribute("book");%>

<div style="width:100%">
	<table border="1">
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
	    <tr>
            <td><%=book.getTitle() %></td>            
            <td><%=book.getDescription()%></td>
            <td><%=book.getAuthorId() %></td>
            <td><%=book.getEditor()%></td>
            <td contenteditable="true" name="price"><%=book.getPrice()%></td>
            <td contenteditable="true"><%=book.getQuantity()%></td>
	    </tr>
	    
	</table>
</div>

<% book.setPrice();
%>
</body>
</html>