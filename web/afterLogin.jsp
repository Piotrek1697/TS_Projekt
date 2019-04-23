<%-- 
    Document   : afterLogin
    Created on : 2019-04-23, 13:15:17
    Author     : Piotr Janus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Witaj!</title>
    </head>
    <body>
        <h1>Jesteś zalogowany jako:</h1>
        <h2><c:set var="user" value="${user}"/>
            <c:out value="${user.name}"/> <c:out value="${user.surname}"/>, <c:out value="${user.login}"/>
        </h2>
        
        <a href="index.jsp">Powrót do strony głównej</a>
    </body>
</html>
