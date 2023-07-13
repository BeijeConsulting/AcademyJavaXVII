<%@page import="web.rubrica.caroselli.Contact"%>
<%@page import="web.rubrica.caroselli.RubricaUtilsJPA"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert a contact</title>
</head>
<body>
    <form action="menu" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>
        
        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname"><br>
       
        <label for="note">Note:</label>
         <input type="text" id="note" name="surname"><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>