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
        <style>
            
            ::-webkit-input-placeholder {
                font-style: italic;
            }
            :-moz-placeholder {
                font-style: italic;  
            }
            ::-moz-placeholder {
                font-style: italic;  
            }
            :-ms-input-placeholder {  
                font-style: italic; 
            }

        </style>
    </head>
    <body>
        <form action="adverts" method="GET" >
            <b>Marka:</b> <input type="text" name="brand" placeholder="Wpisz markę samochodu">
            <b>Model:</b> <input type="text" name="model" placeholder="Wpisz model samochodu"><br><br>
            <input type="submit" value="Szukaj" method="GET">
        </form>
    </body>
</html>
