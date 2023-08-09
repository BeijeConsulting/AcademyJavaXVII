<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add an Author</title>
<style>
	body {
	    font-family: "Roboto", sans-serif;
	    background-color: #fafafa;
	}
	
	.container {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    justify-content: center;
	    height: 100vh;
	}
	
	h1 {
	    font-size: 24px;
	    color: #333;
	    margin-bottom: 16px;
	}
	
	form {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	}
	
	input {
	    margin-bottom: 8px;
	    padding: 8px;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    width: 200px;
	}

	
	a {
	    color: #2196f3;
	    text-decoration: none;
	}
	
	.button-container {
    text-align: center;
    width: 100%;
	}
	
	.button_insert {
	    background-color: #04AA6D;
	    color: white;
	    border: none;
	    padding: 8px 16px;
	    border-radius: 4px;
	    cursor: pointer;
	    width: 220px;
	    margin-top: 16px;
	}
	
	.button_home {
         display: flex;
       	 justify-content: flex-start; 
         gap: 10px;
        }
        
    .button {
        	background-color: #2c5e29; 
        	color: white; 
        	border: none; 
        	padding: 8px 16px; 
        	border-radius: 4px; 
        	cursor: pointer;
        }
	
</style>
</head>
<body>
<div class="button_home">
	<form action="./bookstore_welcome" method="GET">
	   <button class = "button" type="submit">Home</button>
	</form>  
 </div>

    <div class="container">
        <h1>Add an author</h1>
        <form action="./bookstore_insert_author" method="POST">
            <div>
                <label for="name"><b>Name : </b></label><br>
                <input type="text" placeholder="Enter a name" name="name" id="name" required><br>
                
                <label for="surname"><b>Surname : </b></label><br>
                <input type="text" placeholder="Enter a surname" name="surname" id="surname"><br>
                
                <label for="description"><b>Description : </b></label><br>
                <input type="text" placeholder="Enter a description" name="description" id="description"><br>

                <div class="button_container">
                    <button type="submit" class="button_insert">Add</button>
                </div>
            </div>
        </form>
    </div>
  

</body>
</html>