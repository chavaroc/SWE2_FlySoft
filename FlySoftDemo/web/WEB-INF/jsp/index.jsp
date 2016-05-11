<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

            <form:form method="POST" action="/FlySoftDemo/setting" commandName="settingForm">

                <div class="dropdown-area" id="y_area">Set y-axis
                    <br>
                    <select id="y_qualifier" style="margin-top: 5px">
                        <option value="frequencies">Frequencies</option>
                        <option value="count_of_passengers">Count of passengers</option>
                        <option value="delay_frequencies">Delay frequencies</option>
                        <option value="delay_frequencies">Delay durations</option>
                        <option value="cancellations">Cancellations</option>
                    </select>
                    <br><br>
                    Set x-axis
                    <select id="x_qualifier">
                        <option value="airline">Airline</option>
                        <option value="time">Time</option>
                        <option value="destination">Destination</option>
                        <option value="origin">Origin</option>
                    </select>
                    <br><br>
                    Set 3rd dimension
                    <select id="3d_qualifier">
                        <option value="airline">Airline</option>
                        <option value="time">Time</option>
                        <option value="destination">Destination</option>
                        <option value="origin">Origin</option>
                    </select>

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
                <form>
                    <fieldset>
                        <legend>Time</legend>
                        <input type="radio" name="timeunit" value="month" checked> Month<br>
                        <input type="radio" name="timeunit" value="week"> Week<br>
                        <input type="radio" name="timeunit" value="day"> Day
                        <!-- Hier kommt noch der Daterangepicker rein!-->
                    </fieldset>
                </form> 
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