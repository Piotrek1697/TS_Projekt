<%-- 
    Document   : userAdverts
    Created on : 2019-04-24, 17:23:08
    Author     : Piotr Janus
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twoje ogloszenia</title>
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
                width:78%;
                margin-left:0px;
                margin-bottom: 0px;
            }
        </style>
        
    </head>
    <body>
        <a href="index.jsp" style="float: right;margin: 5px;">Powrót do strony głównej</a>
        <h1>Ogłoszenia użytkownika</h1>
        <h2><c:set var="user" value="${user}"/>
            <c:out value="${user.name}"/> <c:out value="${user.surname}"/>, <c:out value="${user.login}"/>
        </h2>
        <c:forEach var="advert" items="${advertList}">
            <div class="advert">
                <form action="userAdverts" method="POST">
                    <input type="submit" value="X" name="deleteButton"  style="float: right;margin: 5px;">
                    <input type="hidden" name="deleteBTN" value="${advert.idAdvertisment}" method="POST">
                </form>
                <form action="modifyAdvert" method="GET">
                    <input type="submit" value="Modyfikuj" name="deleteButton"  style="float: right;margin: 5px;">
                    <input type="hidden" name="modifyBTN" value="${advert.idAdvertisment}" method="GET">
                </form>
                <h2 class="split-para"><c:out value="${advert.idCar.brand}"/>  <c:out value="${advert.idCar.model}"/>  
                    <c:out value="${advert.idCar.yearOfProduction}"/>
                    
                    <span><img src="data:image/jpg;base64,${advert.base64Image}" width="340px" height="220px"/></span></h2>
                    
                <p><c:out value="${advert.idCar.fuelType}"/>  <c:out value="${advert.idCar.engineCapacity}"/>
                <c:out value="${advert.idCar.horsepower} KM"/></p>
                <p><c:out value="${advert.carMileage} km"/></p>
                <h3><c:out value="${advert.price} zł"/></h3>
                <h4>VIN: <c:out value="${advert.vin}"/></h4>
                <br>
            </div>
        </c:forEach>
        
    </body>
</html>
