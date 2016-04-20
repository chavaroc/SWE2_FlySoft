<%-- 
    Document   : response
    Created on : 18.04.2016, 14:08:53
    Author     : xYrs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
   <body>

<h2>Congratulation! You have submitted following setting information successfully:</h2>
   <table>
    <tr>
        <td>X-Axis</td>
        <td>${xaxis}</td> <!--must be equal with SettingController.addStudent parameter-->
    </tr>
    <tr>
        <td>Y-Axis</td>
        <td>${yaxis}</td>
    </tr>
    <tr>
        <td>Arilines</td>
        <td>${arilines}</td>
    </tr>
</table>  
</body>
</html>
