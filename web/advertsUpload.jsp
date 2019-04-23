<%-- 
    Document   : advertsUpload
    Created on : 2019-04-23, 14:48:47
    Author     : Rozma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="advertsUpload" method="POST"> 
        <table width="400px" align ="center" border= 2>
            <tr>
                <td align = "center" colspan ="2"><font size="16">Ogłoszenie</font></td>
            </tr>
            <tr>
                <td>Cena (PLN)</td>
                <td><input type="number" name = "cena"/></td>
            </tr>
            <tr>
                <td>Przebieg (km)</td>
                <td><input type="number" name = "przebieg"/></td>
            </tr>
            <tr>
                <td>Uszkodzony</td>
                <td>
                    <input type="checkbox" name='uszkodzony' value='1'/> TAK 
                </td>
             </tr>
             <tr>
                <td>VIN</td>
                <td>
                    <input type="text" name = "VIN"/>
                </td>
             </tr>                  
             <tr>
                 <td>Opis</td>
                <td><input type="text" name = "opis"/></td>
             </tr>
             <tr>
                 <td>Dodaj</td>
                <td><input type="submit" value="Dodaj ogłoszenie"/></td>
             </tr>
             
        </table>
        </form>
    </body>
</html>
