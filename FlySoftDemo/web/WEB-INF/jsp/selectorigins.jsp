<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FlyAmerica | ValueSelection</title>
        <link type="text/css" rel="stylesheet" href="../styles/flyAmerica.css">
    </head>
    <body>

        <form>
            <fieldset>
                <legend>Orgin Citys</legend>
                <input type="checkbox" name="origin_citys" value="all" checked> ALL<br>
                <input type="checkbox" name="origin_citys" value="New York" checked> New York<br>
                <input type="checkbox" name="origin_citys" value="Las Vegas" checked> Las Vegas<br>
                <input type="checkbox" name="origin_citys" value="Los Angeles" checked> Los Angeles
            </fieldset>
        </form>	

    </body>
</html>