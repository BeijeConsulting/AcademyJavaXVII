<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a Book</title>
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
	
	button {
	    background-color: #04AA6D;
	    color: white;
	    border: none;
	    padding: 8px 16px;
	    border-radius: 4px;
	    cursor: pointer;
	    width: 220px;
	    margin-top: 16px;
	}
	 select {
        width: 220px;
        margin-bottom: 8px;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        background: white;
    }


	
</style>
</head>
<body>

    <div class="container">
        <h1>Add a book</h1>
        <form action="./bookstore_insert_book" method="POST">
            <div>
                <label for="title"><b>Title : </b></label><br>
                <input type="text" placeholder="Enter a title" name="title" id="title" required><br>
                <label for="description"><b>Description : </b></label><br>
                <input type="text" placeholder="Enter a description" name="description" id="description"><br>
                <label for="editor"><b>Editor : </b></label><br>
                <input type="text" placeholder="Enter an editor" name="editor" id="editor"><br>
                <label for="price"><b>Price : </b></label> $<br>
                <input type="number" placeholder="Enter a price" name="price" id="price" required><br>
                <label for="quantity"><b>Quantity : </b></label><br>
                <input type="number" placeholder="Enter the quantity" name="quantity" id="quantity" required><br>
                <label for="author"><b>Author:</b></label><br>
                
                <select name="author" id="author" required>
                    <c:forEach items="${authors}" var="author" varStatus="loop">
                        <option value="${author.id}">${author.surname}</option>
                    </c:forEach>
                </select><br>
                <div class="button_container">
                    <button type="submit">Add</button>
                </div>
            </div>
        </form>
    </div>
  

</body>
</html>