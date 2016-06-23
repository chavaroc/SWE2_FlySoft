<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href='<c:url value="/resources/styles/flyAmerica.css" />' rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='<c:url value="/resources/scripts/jquery-1.12.3.js" />'></script>
        <script src='<c:url value="/resources/scripts/graph.js" />'></script>
    </head>
    <body>
        <div class="enter">
            <div id="header">
                <img src='<c:url value="/resources/imgs/flyAmerica_logo.PNG" />' alt="FlyAmerica-Logo">
            </div>
            <br>
            <div>Welcome FlyAmerica analiyzer!</div>
            <br>
            <form action="workarea">
                <fieldset>
                    <legend>Please enter your username:</legend>
                    <input name="username" type="text"  value="e.g.: John Smith">
                </fieldset>
                <br/>
                <input type="submit" value="Go to workarea">
            </form>
            
        </div>
            
    </body>
</html>