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
        <!-- <spring:url value="/resources/theme1/styles/flyAmerica.css" var="flyAmericaCss"/> 
         <link href="${flyAmericaCss}" rel="stylesheet"/>
        
        <link href="${pageContext.request.contextPath}/resources/theme1/styles/flyAmerica.css" rel="stylesheet" >
        -->

        <link href="<c:url value='/resources/theme1/styles/flyAmerica.css' />" rel="stylesheet">

        <script type="text/javascript" src="scripts/graph.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <!--<script src="https://code.highcharts.com/modules/exporting.js"></script> ggf. fuer pdf export-->
    </head>

    <div id="content">
        <div id="header">
            <img src="imgs/flyAmerica_logo.png" alt="FlyAmerica-Logo" id="logo">
            <div id="username">Max Mustermann</div>
        </div>


        <div class="left">

            <form:form method="POST" action="/FlySoftDemo/workarea" commandName="settingForm">

                <div class="dropdown-area" id="y_area">
                    Set y-axis<br>
                    <form:select path="yaxis" id="y_qualifier" style="margin-top: 5px">
                        <form:options items="${yaxisList}" />
                    </form:select>
                    <br><br>
                    Set x-axis<br>
                    <form:select path="xaxis">
                        <form:options items="${xaxisList}" />
                    </form:select>
                    <br><br>
                    Set 3rd dimension<br>
                    <form:select path="thirdDimension" id="3d_qualifier">
                        <form:options items="${thirdDimensionList}" />
                    </form:select>
                    <br><br>
                    <br> Set Time dimension<br>
                    <form:radiobuttons path="timeDimension" items="${timeDimensionList}" />
                    </div>
            </form:form>

        </div>
        <div class="right">
            <button name="save_setting_button" onclick="myFunction()" type="submit" style="margin-top: 145px">Save setting</button>
            <button name="load_setting_button" type="submit">Load setting</button>
            <button name="export_button" type="submit">Export to PDF</button>
        </div>
        <div class="center">
            <div id="graph">
                <br><br>
                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
            </div>
            <div id="filters">
                <br>
                <form>
                    <fieldset>
                        <legend>Airlines</legend>
                        <input type="checkbox" name="airlines" value="all" checked> ALL<br>
                        <input type="checkbox" name="airlines" value="month" checked> Month<br>
                        <input type="checkbox" name="airlines" value="week" checked> Week<br>
                        <input type="checkbox" name="airlines" value="day" checked> Day
                    </fieldset>
                </form>
                <br/>

                <br>
                <a href="selectorigins" target="selectorigins" onClick="javascript:open('', 'selectorigins', 'height=400,width=400,resizable=no')">Select Origin(s)</a>
                <a href="selectdestinations" target="select-destinations" onClick="javascript:open('', 'select-destinations', 'height=400,width=400,resizable=no')">Select Destination(s)</a>
            </div>
        </div>
    </div>	
</body>
</html>