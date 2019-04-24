<%-- 
    Document   : modifyAdvert
    Created on : 2019-04-24, 19:29:27
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
        <c:set var="advert" value="${advertisment}"/>
        <form action="modifyAdvert" method="POST"> 
        <table width="400px" align ="center" border= 2>
            <tr>
                <td align = "center" colspan ="2"><font size="14">Dodaj ogłoszenie</font></td>
            </tr>
            <tr>
                <td>Cena (PLN)</td>
                <td><input type="number" name = "cena" value="${advert.price}"/></td>
            </tr>
            <tr>
                <td>Przebieg (km)</td>
                <td><input type="number" name = "przebieg" value="${advert.carMileage}"/></td>
            </tr>
            <tr>
                <td>Uszkodzony</td>
                <td>
                    <input type="checkbox" name='uszkodzony' /> TAK 
                </td>
             </tr>
             <tr>
                <td>VIN</td>
                <td>
                    <input type="text" name = "VIN" value="${advert.vin}"/>
                </td>
             </tr>                  
             <tr>
                 <td>Opis</td>
                <td><input type="text" name = "opis" value="${advert.description}"/></td>
             </tr>
             <tr>
                 <td>Samochód</td>
                 <td>
                     <select id="selectedCar" name="selectedCar">
                        <c:forEach var="car" items="${carList}">
                                <option value="${car.idCar}">${car.brand} ${car.model}, ${car.fuelType} ${car.engineCapacity}</option>
                        </c:forEach>
                                <option selected="selected">
                                    ${advert.idCar.idCar}
                                </option>
                     </select>
                 </td>
             </tr>
             <tr>
                 <td>Dalej</td>
                <td><input type="submit" value="Modyfikuj" method="POST"/></td>
             </tr>
             
        </table>
        </form>
    </body>
</html>
