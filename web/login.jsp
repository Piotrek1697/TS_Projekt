<%-- 
    Document   : login
    Created on : 2019-04-23, 12:06:59
    Author     : Piotr Janus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logowanie</title>
        <link href ="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <style>
            td{
                padding: 10px;
            }
            div {
                
                width: 30%;
                border: 1px solid black;
                border-radius: 5px;
                background-color: lightslategrey;
            }
            
            
        </style>
    </head>
    <body style = "background-color:#C9E0E1;">
        
        <form action="login" method="post">
            <center><h1>Zaloguj się do serwisu</h1></center>
            <center>
            <div
                <table
                    <tr>
                    <td>Login</td>
                    <td><input type="text" class="form-control" name="userLogin" style="width: 300px" ></td>
                    </tr>
                    <tr>
                    <td>Hasło</td>
                    <td><input type="password" class="form-control" name="userPassword" style="width: 300px"></td>
                    </tr>
                    <tr>
                        <td colspan ="2" style ="text-align: center"><input class = "btn btn-success " type="submit" value="Login" style="margin: 10px"></td>
                    </tr>
                
                </table>
            </div>
            </center>
        </form>
    </body>
</html>
