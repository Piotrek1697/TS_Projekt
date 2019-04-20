<%-- 
    Document   : index.jsp
    Created on : 2019-04-11, 17:16:34
    Author     : Piotr Janus
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Lista</h2>
        <table style="width:50%" border="1">
            <tr>
                <th>ID</th>
                <th>Tytul</th>
                <th>Autor</th>
                <th>ISBN</th>
                <th>Status</th>
                <th>Description</th>
            </tr>
            <c:forEach var="book" items="${bookList}">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.isbn}"/></td>
                    <td><c:out value="${book.status}"/></td>
                    <td><c:out value="${book.description}"/></td>
            </tr>
            </c:forEach>
        </table>
              
              <form action="FirstServlet" method="POST">
                  id: <input type="text" name="id"><br>
                  title: <input type="text" name="title"><br>
                  author: <input type="text" name="author"><br>
                  isbn: <input type="text" name="isbn"><br>
                  short description: <input type="text" name="desc"><br>
                  <input type="radio" name="answer" value="add">Add<br>
                  <input type="radio" name="answer" value="modify">Modify<br>
                  <input type="radio" name="answer" value="delete">Delete<br>
                  <input type="submit" value="Confirm">
              </form>
        <form action="FirstServlet" method="GET">
            <input type="submit" value="Get all values" method="GET">
        </form>
        
    </body>
</html>
