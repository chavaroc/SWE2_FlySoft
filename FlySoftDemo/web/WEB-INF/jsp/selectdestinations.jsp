<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <title>FlyAmerica | Statistics</title>

        <spring:url value="/resources/theme1/styles/flyAmerica.css" var="flyAmericaCss"/> 
        <link href="${flyAmericaCss}" rel="stylesheet"/>

        <script type="text/javascript" src="scripts/graph.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <!--<script src="https://code.highcharts.com/modules/exporting.js"></script> ggf. fuer pdf export-->
    </head>
    <body>

        <form:form method="POST" action="/FlySoftDemo/selectdestinations/cities" commandName="cityForm">
            <form:checkboxes path="cities" items="${cityNameList}" />
        </form:form>

    </body>
</html>