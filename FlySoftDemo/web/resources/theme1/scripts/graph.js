/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {

    var title_text = "";
    var subtitle_text = "";
    var graph_type = "spline";
    var x_axis_name = "Time";
    var y_axis_name = "Frequencies";
    var resultFromServer;
    var selected_3d_val;
    var data_serie;
    var x_axis_unit;


    $("#destinations_link").hide();
    $("#timeDimension_selector").hide();
    $("#weekday_selector").hide();
    $('#3d_selector option').filter(":eq( 1 )").attr("disabled", "");

    $.redraw = function () {
        $('#container').highcharts({
            chart: {
                type: graph_type
            },
            title: {
                text: title_text
            },
            subtitle: {
                text: subtitle_text
            },
            xAxis: {
                /*type: 'text',
                 dateTimeLabelFormats: {// don't display the dummy year
                 month: '%e. %b',
                 year: '%b'
                 },*/
                title: {
                    text: x_axis_name
                }
            },
            yAxis: {
                title: {
                    text: y_axis_name
                },
                min: 0
            },
            tooltip: {
                //headerFormat: '<b>{series.name}</b><br>',
                pointFormat: '{point.y}'
            },
            plotOptions: {
                spline: {
                    marker: {
                        enabled: true
                    }
                }
            },
            series: data_serie
        });
    };

    $.updateDataToPlot = function () {
        //TODO Anpassen!
        data_serie = [{data: resultFromServer}]; //irgendwas in der Art
        $.redraw(); //always at the beginning with default values
    };


    $.redraw(); //always at the beginning with default values	


    $("#xaxis_selector").change(function () {
        $('#3d_selector option').filter(function (i, e) {
            return $(e).text() === x_axis_name;
        }).removeAttr("disabled");
        $('#3d_selector option').filter(":eq( 1 )").removeAttr("disabled");
        x_axis_name = $("#xaxis_selector option:selected").text();
        if (x_axis_name === "Time") {
            $("#destinations_link").hide();
            $("#airlines_selector").hide();
            $("#timeDimension_selector").show();
        } else if (x_axis_name === "Destination") {
            $("#destinations_link").show();
            $("#airlines_selector").hide();
            $("#timeDimension_selector").hide();
            $("#weekday_selector").hide();
        } else if (x_axis_name === "Airline") {
            $("#destinations_link").hide();
            $("#airlines_selector").show();
            $("#timeDimension_selector").hide();
            $("#weekday_selector").hide();
        }
        $('#3d_selector option').filter(function (i, e) {
            return $(e).text() === x_axis_name;
        }).attr("disabled", "");
    });

    $("#3d_selector").change(function () {
        $('#xaxis_selector option').filter(function (i, e) {
            return $(e).text() === selected_3d_val;
        }).removeAttr("disabled");
        if (selected_3d_val === "Airline") {
            $("#airlines_selector").hide();
        } else if (selected_3d_val === "Time") {
            $("#timeDimension_selector").hide();
            $("#weekday_selector").hide();
        } else if (selected_3d_val === "Destination") {
            $("#destinations_link").hide();
        }
        selected_3d_val = $("#3d_selector option:selected").text();
        if (selected_3d_val === "Airline") {
            $("#airlines_selector").show();
        } else if (selected_3d_val === "Time") {
            $("#timeDimension_selector").show();
        } else if (selected_3d_val === "Destination") {
            $("#destinations_link").show();
        }
        $('#xaxis_selector option').filter(function (i, e) {
            return $(e).text() === selected_3d_val;
        }).attr("disabled", "");
    });

    $("#y_qualifier").change(function () {
        y_axis_name = $("#y_qualifier option:selected").text();
        console.log(y_axis_name);
        if (y_axis_name === "Count of passengers") {
            $("#time_dimension1").attr("disabled", "");
            $("#time_dimension2").attr("disabled", "");
            $("#time_dimension3").attr("disabled", "");
            $("#time_dimension1").attr("checked", false);
            $("#time_dimension2").attr("checked", false);
            $("#time_dimension3").attr("checked", false);
            $("#weekday_selector").hide();
        } else {
            $("#time_dimension1").removeAttr("disabled");
            $("#time_dimension2").removeAttr("disabled");
            $("#time_dimension3").removeAttr("disabled");
        }
    });

    $("#timeDimension_selector input").on("click", function () {
        if ($("#timeDimension_selector input:checked").val() === "Weekday(s)") {
            $("#weekday_selector").show();
        } else {
            $("#weekday_selector").hide();
        }
    });

    var options = [];

    $('.dropdown-menu a').on('click', function (event) {

        var $target = $(event.currentTarget),
                val = $target.attr('data-value'),
                $inp = $target.find('input'),
                idx;

        if ((idx = options.indexOf(val)) > -1) {
            options.splice(idx, 1);
            setTimeout(function () {
                $inp.prop('checked', false)
            }, 0);
        } else {
            options.push(val);
            setTimeout(function () {
                $inp.prop('checked', true)
            }, 0);
        }

        $(event.target).blur();

        console.log(options);
        return false;
    });

    $("#submit_button").click(function () {
        var xaxis = $("#xaxis_selector option:selected").text(); //"Time";
        var yaxis = $("#y_qualifier option:selected").text();
        var timedim = $('input[name="timeDimension"]:checked').val();
        var thirddim = $("#thirdDimension option:selected").text();
        var destinations = "Las Vegas, NV";
        var timerange = [$('input[name="startDate"]').val(), $('input[name="endDate"]').val()];
        
        var airlines = $('input[name="airline"]:checked').map(function () {
            return this.value;
        }).get();
        
        var weekdays = $('input[name="weekday"]:checked').map(function () {
            return this.value;
        }).get();



        console.log(yaxis);
        console.log(xaxis);
        console.log(timerange);
        console.log(airlines);
        console.log(weekdays);

        var url = "/FlySoftDemo/workarea/graphdata";
        $.getJSON(url, {xaxis: escape(xaxis), yaxis: yaxis, timedim: timedim, thirddim: thirddim, destinations: destinations, timerange: timerange, airlines: airlines}, function (json) {
            console.log(json);
            resultFromServer = json;
            //update label-names for graph

            if (xaxis === "Time") {
                x_axis_name = "Time in " + timedim + "s";
            } else {
                x_axis_name = xaxis;
            }

            y_axis_name = yaxis;
            $.updateDataToPlot(); //Zu plottende Daten aktualisieren neu zeichnen lassen
        });



    });

});
