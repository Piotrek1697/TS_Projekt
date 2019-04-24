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
        <title>Wyszukaj samochód</title>
        <link rel="stylesheet" href="indexStyle.css">
 
    </head>
    <body>
        
        <form class="registrationButton" action="registration" method="GET">
            <input type="submit" value="Zarejetruj się" method="GET">
        </form>
        
        <form class="loginButton" action="login" method="GET">
            <input type="submit" value="Zaloguj się" method="GET">
        </form>
        
        <form  class="myAdverts"action="userAdverts" method="GET" >
            <input type="submit" value="Moje ogloszenia" method="GET">
        </form>
        
        <form class="box"action="adverts" method="GET">
            <h1>Szukaj samochód</h1>
                    <input type="text" name="brand" placeholder="Wpisz markę samochodu">
                    <input type="text" name="model" placeholder="Wpisz model samochodu">
                    <input type="submit" value="Szukaj" method="GET">
                      
        </form>
         <form class="addAdvert" action="advertsUpload" method="GET" >
            <input type="submit" value="Dodaj ogłoszenie" method="GET" >
        </form>
        
        
    </body>
</html>
