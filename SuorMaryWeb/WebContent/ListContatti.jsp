<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="it.beije.suormary.web.sala.Contatto" %>
     <%@ page import="it.beije.suormary.web.sala.RubricaUtilsJPA" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Contatto> contacts = RubricaUtilsJPA.loadRubricaFromDBCON();%>
  <h1>Rubrica contatti</h1>
   
        <% for (Contatto contact : contacts) { %>
            <%= contact.getName() %></br>
                <td><%= contact.getSurname() %></br>
                <td><%= contact.getPhoneNumber() %></br>
                <td><%= contact.getEmail() %></br>
                <td><%= contact.getNote() %></br>
            
        <% } %>
 
</body>
</html>