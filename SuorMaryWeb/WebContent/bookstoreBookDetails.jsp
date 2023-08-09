<%@page import="it.beije.suormary.bookstore2.model.User"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="it.beije.suormary.bookstore2.model.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book description</title>

	<style>
		.container {
			display: flex; 
			flex-wrap: wrap; 
			gap: 20px;
		}
		.book {
			width: 300px; 
			border: 1px solid #ccc; 
			padding: 10px; 
			background-color: #f9f9f9;
			border-radius: 14px;
		}
		.title {
        	text-align: center; 
        	color: #333;
        }
        .paragraph {
        	text-align: center;
        }
        .button {
        background-color: #2c5e29;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        }
        
        .button[disabled] {
          background-color: #ccc;
          color: #666;
          cursor: not-allowed;
        }
       
	</style>
</head>
<body>

<% Book book = (Book) request.getAttribute("book");%> 
<% Author author = (Author) request.getAttribute("author");%> 
  <div class="container">
    <c:if test="${not empty book}">
        <div class="book">
            <h2 class="title">${book.title}</h2>
            <hr>
            <p class="paragraph"><strong>Author :</strong> ${author.name} ${author.surname}</p>
            <p class="paragraph"><strong>Description :</strong> ${book.description}</p>
            <p class="paragraph"><strong>Editor :</strong> ${book.editor}</p>
            <p class="paragraph"><strong>Price :</strong> $${book.price}</p>
            <p class="paragraph"><strong>Quantity :</strong> ${book.quantity}</p>

            <c:set var="quantity" value="${cart[book.id]}" />
            <c:set var="maxQuantity" value="${book.quantity}" />
            <c:set var="addButtonDisabled" value="${quantity >= maxQuantity}" />

            <c:choose>
                <c:when test="${addButtonDisabled}">
                    <form class="paragraph">
                        <button type="submit" class="button"
                                title="Maximum amount of books reached for this book."
                                disabled>Add to Cart
                        </button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form class="paragraph" action="./cart" method="post">
                        <input type="hidden" name="id" value="${book.id}">
                        <input type="hidden" name="action" value="addFromBookDet">
                        <button type="submit" class="button">Add to Cart</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${empty book}">
        <div class="paragraph">
            <p class="title">No book found.</p>
        </div>
    </c:if>
</div>
</body>
</html>