<%-- 
    Document   : registration
    Created on : 2019-04-23, 12:41:21
    Author     : Piotr Janus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rejestracja</title>
        <link rel="stylesheet" href="registrationStyle.css">
    </head>
    <body>
         <form class="box" action="registration" method="post" style="margin: 10px" align="center">
            <h1 align="center">Zarejestruj się</h1>
            <input type="text" name="userName" placeholder="imię">
            <input type="text" name="userSurname" placeholder="nazwisko">
            <input type="text" name="userLogin" placeholder="login">
            <input type="password" name="userPassword" placeholder="hasło">
            <input type="submit" value="Rejestracja" method="POST">
        </form>
    </body>
</html>
