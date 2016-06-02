/*
 * JavaScript File for GUI on Client-Site.
 * Using JQuery- and Highcharts- API.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {

    var title_text = "";        // title of the highcharts-graph -> currently none
    var subtitle_text = "";     // subtitle of the highcharts-graph -> currently none
    var graph_type = "spline";  // for more detail, see Highcharts-API-Documentation
    var x_axis_name = "Time";   // label of x_axis -> Default (when page has loaded at the beginning): Time
    var y_axis_name = "Frequencies";    // label of y_axis -> Default (when page has loaded at the beginning): Frequencies
    var resultFromServer;       // Received Data from Server. Data to Plot in Graph.    
    var data_serie;             // Data to plot. For more detail, see Highcharts-API-Documentation            

    // Hiding of filter-settings, that are not changeable at the beginning, because of the default-constellation of the axis-settings.
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
        data_serie = [{data: resultFromServer}]; //something like this
        $.redraw(); //always at the beginning with default values
    };


    $.redraw(); //always at the beginning with default values -> crates empty graph

    /**
     * Checks / Unchecks all airlines.
     */
    $("#check_all_airlines").change(function () {
        $("input:checkbox[name='airline']").prop('checked', $(this).prop("checked"));
    });

    /**
     * Checks / Unchecks all weekdays.
     */
    $("#check_all_weekdays").change(function () {
        $("input:checkbox[name='weekday']").prop('checked', $(this).prop("checked"));
    });

    /**
     * Hides or shows changeable filter-settings, depending on filter-criteria at the x-axis.
     */
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

    /**
     * Hides or shows changeable filter-settings, depending on filter-criteria at the 3rd dimension.
     */
    $("#3d_selector").change(function () {
        var selected_3d_val;
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

    /**
     * Hides or shows changeable filter-settings, depending on filter-criteria at the y-axis.
     */
    $("#y_qualifier").change(function () {
        y_axis_name = $("#y_qualifier option:selected").text();
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

    /**
     * Hides or shows changeable filter-settings, depending on filter-criteria at the timedimension-selector.
     */
    $("#timeDimension_selector input").on("click", function () {
        if ($("#timeDimension_selector input:checked").val() === "Weekday(s)") {
            $("#weekday_selector").show();
        } else {
            $("#weekday_selector").hide();
        }
    });

    /**
     * Is the following part needed? - Markus: 30-05-16
     * Delete otherwise.
     */
//    var options = [];
//
//    $('.dropdown-menu a').on('click', function (event) {
//
//        var $target = $(event.currentTarget),
//                val = $target.attr('data-value'),
//                $inp = $target.find('input'),
//                idx;
//
//        if ((idx = options.indexOf(val)) > -1) {
//            options.splice(idx, 1);
//            setTimeout(function () {
//                $inp.prop('checked', false)
//            }, 0);
//        } else {
//            options.push(val);
//            setTimeout(function () {
//                $inp.prop('checked', true)
//            }, 0);
//        }
//
//        $(event.target).blur();
//
//        console.log(options);
//        return false;
//    });

    $("#submit_button").click(function () {
        var xaxis = $("#xaxis_selector option:selected").text();        // selected filter for x-axis
        var yaxis = $("#y_qualifier option:selected").text();           // selected filter for y-axis
        var timedim = $('input[name="timeDimension"]:checked').val();   // selected timedimension
        var thirddim = $("#thirdDimension option:selected").text();     // selected third dimension
        /**
         * TODO: Betina
         * maybe like at airlines and weekdays
         * @type String
         */
        var destinations = $('input[name="destination"]:checked').map(function () { //selected destinations
            return this.value;
        }).get();
        var timerange = [$('input[name="startDate"]').val(), $('input[name="endDate"]').val()]; //selected timerange
        var airlines = $('input[name="airline"]:checked').map(function () { //selected airlines
            return this.value;
        }).get();
        var weekdays = $('input[name="weekday"]:checked').map(function () { //selected weekdays
            return this.value;
        }).get();

        // Logging for testing, during development
        console.log(yaxis);
        console.log(xaxis);
        console.log(destinations);
        console.log(timerange);
        console.log(airlines);
        console.log(weekdays);
        

        // sending information to querybuilder and receiving plotable data from server
        var url = "/FlySoftDemo/workarea/graphdata";
        $.getJSON(url, {xaxis: escape(xaxis), yaxis: yaxis, timedim: timedim, thirddim: thirddim, destinations: destinations, timerange: timerange, airlines: airlines}, function (json) {
            console.log(json);
            resultFromServer = json;

            //update axis-/labelnames for graph
            if (xaxis === "Time") {
                x_axis_name = "Time in " + timedim + "s";
            } else {
                x_axis_name = xaxis;
            }
            y_axis_name = yaxis;

            // updates data to plot and updates graph
            $.updateDataToPlot();
        });
    });

});
