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
    
</head>

<div id="content">
    <div id="header">
        <img src='<c:url value="/resources/imgs/flyAmerica_logo.PNG" />' alt="FlyAmerica-Logo" id="logo">
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
            

            <form:form method="POST" action="/FlySoftDemo/workarea" commandName="airlineForm">
                <%-- <form:checkboxes path="airlinesnew" items="${airlinenewNameList}" /> --%>
                
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

            <br>
            <a href="selectdestinations/cities" target="select-destinations" onClick="javascript:open('', 'select-destinations', 'height=400,width=400,resizable=no')">Select Destination(s)</a>
            <a href="selectorigins" target="selectorigins" onClick="javascript:open('', 'selectorigins', 'height=400,width=400,resizable=no')">Select Origin(s)</a>
        </div>
    </div>
</div>	
</body>
</html>