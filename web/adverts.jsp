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
        <link rel="stylesheet" href="advertDisplayStyle.css">
        
    </head>
    <body>
        <a href="index.jsp" class="back">Powrót do strony głównej</a>
        <h1 class = "text">Ogłoszenia</h1>
        <c:forEach var="advert" items="${advertList}">
            <div class="advert">
                <h2 class="split-para"><c:out value="${advert.idCar.brand}"/>  <c:out value="${advert.idCar.model}"/>  
                    <c:out value="${advert.idCar.yearOfProduction}"/>
                    
                    <span><img src="data:image/jpg;base64,${advert.base64Image}" width="340px" height="220px"/></span></h2>
                    
                <p><c:out value="${advert.idCar.fuelType}"/>  <c:out value="${advert.idCar.engineCapacity}"/>
                <c:out value="${advert.idCar.horsepower} KM"/></p>
                <p><c:out value="${advert.carMileage} km"/></p>
                <h3><c:out value="${advert.price} zł"/></h3>
                <h4>VIN: <c:out value="${advert.vin}"/></h4>
                <h5>Opis: <c:out value="${advert.description}"/></h5>
               <br>
            </div>
        </c:forEach>
        
    </body>
</html>
