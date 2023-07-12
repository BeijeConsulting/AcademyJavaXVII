<%@page import="java.util.List" %>
<%@page import="web.rubrica.caroselli.Contact" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Contatti</title>
</head>
<body>
    <p>Hello world</p>
    <%
    List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
    %>
   
    <% if (contacts != null) {
        for (Contact contact : contacts) { %>
            <p>Element: <%= contact.getName() %></p>
    <%  }
       } %>
  
</body>
</html>