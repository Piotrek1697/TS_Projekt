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
    </head>
    <body>
       <h1 align="center">Zarejestruj się</h1>
        <form action="registration" method="post" style="margin: 10px" align="center">
            Imię: <input type="text" name="userName"><br>
            Nazwisko: <input type="text" name="userSurname"><br>
            Login: <input type="text" name="userLogin"><br>
            Hasło: <input type="password" name="userPassword"><br>
            
            <input type="submit" value="Rejestracja" method="POST" style="margin: 10px">
        </form>
    </body>
</html>
