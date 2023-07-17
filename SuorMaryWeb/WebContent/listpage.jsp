<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Book"%>
<%@ page import="it.beije.suormary.bookstore4_ceccarelli_iannetta.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List</title>
</head>
<body>

<%
String authorError = (String) session.getAttribute("AuthorError");
if (authorError != null) {
	%>
	<h2 style="color:red; text-align: center"><%= authorError %></h2>
	<%
	session.removeAttribute("AuthorError");
}
String bookError = (String) session.getAttribute("BookError");
if (bookError != null) {
	%>
	<h2 style="color:red; text-align: center"><%= bookError %></h2>
	<%
	session.removeAttribute("BookError");
}
List<Book> books = (ArrayList<Book>)(session.getAttribute("allBooks"));

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
<% if (books == null) {%>
	
	<tr><td style="color:red">LISTA LIBRI VUOTA</td></tr>
	<%
	}else {%>
	
		<%for(int i=0;i<books.size();i++){
			%>
			<tr>
	            <td><%=books.get(i).getTitle() %></td>            
	            <td><%=books.get(i).getDescription()%></td>
	            <td><%=books.get(i).getAuthorId() %></td>
	            <td><%=books.get(i).getEditor()%></td>
	            <td ><%=books.get(i).getPrice()%></td>
	            <td ><%=books.get(i).getQuantity()%></td>
	            <%session.setAttribute("book", books.get(i));%>
	            <td><a href="updatepage.jsp" ><button>E</button></a></td>
		    </tr>
		<%	}	
	}%>	</tbody>
	</table>
	<br>
	<div style="text-align: center">
	<a href="addBook.jsp"><button style="border:solid; border-width: 1px; " >Add book</button></a>
	</div>
	
	</div><%
List<Author> authors = (ArrayList<Author>)(session.getAttribute("allAuthors"));
%><div style="width:32%; float:right">
	<h3 style="text-align: center">AUTHORS</h3>
	<table border="1">
	    <!-- Intestazione della tabella -->
	    <thead>
	        <tr>
	        	<th>ID </th>
	            <th>Surname</th>
	            <th>Name</th>
	            <th>Description</th>
	        </tr>
	        
	    </thead>
	    <tbody><%
if (authors == null) {
	%>
	<tr><td style="color:red">LISTA AUTORI VUOTA</td></tr>
	<%
}else {%>
		<%for(int i=0;i<authors.size();i++){
			%>
			<tr>
				<td><%=authors.get(i).getId() %></td>
	            <td><%=authors.get(i).getSurname() %></td>
	            <td><%=authors.get(i).getName()%></td>
	            <td ><%=authors.get(i).getDescription()%></td>
		    </tr>
		<%	}
			
	}%></tbody>
	</table>
	<br>
	<div style="text-align: center">
	<a href="addAuthor.jsp"><button style="border:solid; border-width: 1px; " >Add author</button></a>
	</div>
	
	</div>
	</div>
</body>
</html>