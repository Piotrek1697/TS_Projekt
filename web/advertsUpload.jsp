<%-- 
    Document   : advertsUpload
    Created on : 2019-04-23, 14:48:47
    Author     : Rozma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="addAdvertStyle.css">
    </head>
    <body>
        <form action="advertsUpload" method="POST"> 
        <table width="400px" align ="center" border= 2 class = "table">
            <tr>
                <td align = "center" colspan ="2"><font size="14">Dodaj ogłoszenie</font></td>
            </tr>
            <tr>
                <td>Cena (PLN)</td>
                <td><input type="number" name = "cena" class = "inputField"/></td>
            </tr>
            <tr>
                <td>Przebieg (km)</td>
                <td><input type="number" name = "przebieg" class = "inputField" /></td>
            </tr>
            <tr>
                <td>Uszkodzony</td>
                <td>
                    <input type="checkbox" name='uszkodzony'/>TAK 
                </td>
             </tr>
             <tr>
                <td>VIN</td>
                <td>
                    <input type="text" name = "VIN" class = "inputField"/>
                </td>
             </tr>                  
             <tr>
                 <td>Opis</td>
                <td><input type="text" name = "opis" class = "inputField" /></td>
             </tr>
             <tr>
                 <td>Samochód</td>
                 <td>
                     <select id="selectedCar" name="selectedCar" class="listBox">
                        <c:forEach var="car" items="${carList}">
                                <option value="${car.idCar}">${car.brand} ${car.model}, ${car.fuelType} ${car.engineCapacity}</option>
                        </c:forEach>
                     </select>
                 </td>
             </tr>
             <tr>
                 <td>Dalej</td>
                 <td><input type="submit" value="Dalej" method="POST" class="button"/></td>
             </tr>
             
        </table>
        </form>
    </body>
</html>
