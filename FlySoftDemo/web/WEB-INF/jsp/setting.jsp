<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a setting</title>
    </head>
    <body>
        <h2>Add new setting</h2>
        <form:form method="POST" action="/FlySoftDemo/setting" commandName="settingForm">
               <table>
                <tr>
                    <!--for xaxis there must be a setXaxis and getXaxis in model class assoziated with 'settingForm'-->
                    <td><form:label path="xaxis">X-Axis</form:label></td>
                    <td><form:select path="xaxis">
                            <form:options items="${xaxisList}" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="yaxis">Y-Axis</form:label></td>
                    <td><form:select path="yaxis">
                            <form:options items="${yaxisList}" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    
                    <td><form:label path="airlines">Airlines</form:label></td>
                    <!--<td><form:input path="airlines" /></td>-->
                    <td><form:checkboxes items="${airlineList}" path="airlines" /> 
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
            </form:form>
    </body>
</html>
    </body>
</html>
