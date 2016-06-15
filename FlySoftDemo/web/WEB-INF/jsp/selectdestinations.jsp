<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='<c:url value="/resources/scripts/jquery-1.12.3.js" />'></script>
        <title>FlyAmerica | Statistics</title>
    </head>
    <body>

        <form:form method="POST" action="/FlySoftDemo/selectdestinations/cities" commandName="cityForm">
            <fieldset>
                <legend>Destination Citys</legend>
                <form:checkboxes delimiter="<br/>" name="destination" path="cities" items="${cityNameList}" />   
            </fieldset>
        </form:form>
        <br>
        <input style="float: right;" id="submit_destinations" type="button" value="OK" onClick="self.close()"/>    
        <br>
    </body>
</html>