<%-- 
    Document   : adverts.jsp
    Created on : 2019-04-22, 22:43:24
    Author     : Piotr Janus
--%>

<%@page import="java.awt.image.BufferedImage"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ogloszenia samochodowe</title>
        <style>
            .advert {
                background-color: black;
                color: white;
                margin: 20px;
                padding: 20px;
            }
            .split-para { 
                display:block;
                margin:0px;
            }
            .split-para span { 
                display:block;
                float:right;
                width:81%;
                margin-left:0px;
                margin-bottom: 0px;
            }
        </style>
    </head>
    <body>
        <c:forEach var="advert" items="${advertList}">
            <div class="advert">
                <h2 class="split-para"><c:out value="${advert.idCar.brand}"/>  <c:out value="${advert.idCar.model}"/>  
                    <c:out value="${advert.idCar.yearOfProduction}"/>
                    <span><img src="data:image/jpg;base64,${advert.base64Image}" width="20%" height="20%"/></span></h2>
                    
                <p><c:out value="${advert.idCar.fuelType}"/>  <c:out value="${advert.idCar.engineCapacity}"/>
                <c:out value="${advert.idCar.horsepower} KM"/></p>
                <p><c:out value="${advert.carMileage} km"/></p>
                <h3><c:out value="${advert.price} zł"/></h3>
               
            </div>
        </c:forEach>
        
    </body>
</html>