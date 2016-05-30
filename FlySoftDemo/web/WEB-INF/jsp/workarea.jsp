<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <link href='<c:url value="/resources/styles/flyAmerica.css" />' rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='<c:url value="/resources/scripts/jquery-1.12.3.js" />'></script>
        <script src='<c:url value="/resources/scripts/highcharts.js" />'></script>
        <script src='<c:url value="/resources/scripts/graph.js" />'></script>

        <title>FlyAmerica | Statistics</title>

        <!--        <script type="text/javascript">
                    $function(){
                        $("#submit_button").click(function () {
                            alert("test");
                            console.log("pressed");
                        });
                    }
                    ;
                </script>-->

    </head>

    <div id="content">
        <div id="header">
            <img src='<c:url value="/resources/imgs/flyAmerica_logo.PNG" />' alt="FlyAmerica-Logo" id="logo">
            <div id="username">Max Mustermän</div>
        </div>

        <div class="left">

            <form:form method="GET" action="/FlySoftDemo/workarea/graphdata" commandName="settingForm">

                <div class="dropdown-area" id="y_area">
                    Set y-axis<br>
                    <form:select path="yaxis" id="y_qualifier" style="margin-top: 5px">
                        <form:options items="${yaxisList}" />
                    </form:select>
                    <br><br>
                    Set x-axis<br>
                    <form:select id="xaxis_selector" path="xaxis">
                        <form:options items="${xaxisList}" />
                    </form:select>
                    <br><br>
                    Set 3rd dimension<br>
                    <div id="3d_qualifier">
                        <form:select id="3d_selector" path="thirdDimension">
                            <form:options style="background-color:white;" items="${thirdDimensionList}" />
                        </form:select>
                    </div>
                    <br>
                    <div id="timeDimension_selector">
                        <br> Set Time dimension<br>
                        <form:radiobuttons id="time_dimension" path="timeDimension" items="${timeDimensionList}" />
                    </div>
                    <br>

                    <input id="submit_button" type="button" value="Get Results!"/>        
                    <!--                    <input type="submit" value="Get Results!"/>-->
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

                <form:form id="airlines_selector" method="POST" action="/FlySoftDemo/workarea" commandName="airlineForm">
                    <fieldset>
                        <legend>Airlines</legend>
                        <table style="text-align:center">
                            <c:forEach begin="0" end="${fn:length(airlinenewNameList) - 1}" step="2" varStatus="loop"> 
                                <tr>
                                    <td style="text-align: right"><input type="checkbox" name="test" value="${airlinenewNameList[loop.index]}"></td> 
                                    <td style="width:450px">${airlinenewNameList[loop.index]}</td>
                                    <td style="text-align: right"><input type="checkbox" name="test" value="${airlinenewNameList[loop.index + 1]}"></td>
                                    <td style="width:450px">${airlinenewNameList[loop.index + 1]}</td>
                                </tr>
                            </c:forEach>
                        </table>  
                    </fieldset>
                </form:form>
                <br/>
                <form:form id="timerange_selector">
                    <fieldset>
                        <legend>Timerange</legend>
                        Start <input type="date" name="startDate" value="01.01.2015">
                        End <input type="date" name="endDate" value="31.10.2015">
                    </fieldset>
                </form:form>
                <br>
                <form:form id="weekday_selector">
                    <fieldset>
                        <legend>Weekdays</legend>
                        <label> <input type="checkbox" name="weekday" value="all"> all </label>
                        <br>
                        <label> <input type="checkbox" name="weekday" value="Monday"> Monday </label>
                        <label> <input type="checkbox" name="weekday" value="Tuesday"> Tuesday </label>
                        <label> <input type="checkbox" name="weekday" value="Wednesday"> Wednesday </label>
                        <label> <input type="checkbox" name="weekday" value="Thursday"> Thursday </label>
                        <label> <input type="checkbox" name="weekday" value="Friday"> Friday </label>
                        <label> <input type="checkbox" name="weekday" value="Saturday"> Saturday </label>
                        <label> <input type="checkbox" name="weekday" value="schinken"> Sunday </label>
                    </fieldset>
                </form:form>
                <br>
                <a id="destinations_link" href="selectdestinations/cities" target="select-destinations" onClick="javascript:open('', 'select-destinations', 'height=400,width=400,resizable=no')">Select Destination(s)</a>
            </div>
        </div>
    </div>	
</body>
</html>