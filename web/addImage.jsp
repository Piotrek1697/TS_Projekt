<%-- 
    Document   : addImage
    Created on : 2019-04-24, 00:09:56
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
          <form action="advertsUpload" method="POST" enctype="multipart/form-data">
        <table width="400px" align ="center" border= 2>
            <tr>
                <td align = "center" colspan ="2"><font size="14">Dodaj zdjęcie</font></td>
            </tr>
           <tr>
                <td>Dodaj zdjęcie</td>
                <td><input type="file" name ="zdjecie" </td>    
           </tr>
           <tr>
                 <td></td>
                <td><input type="submit" value="Dodaj" method="POST"/></td>
            </tr>
          </table>
        </form>
        
    </body>
</html>
