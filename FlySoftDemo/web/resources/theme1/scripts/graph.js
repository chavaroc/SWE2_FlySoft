/*
 * JavaScript File for GUI on Client-Site.
 * Using JQuery- and Highcharts- API.
 */
$(function () {

    /**
     * Get and set the username.
     */
    document.getElementsByName("username").value;
    document.getElementById("username").innerHTML = document.baseURI.substring(52, document.baseURI.length).replace('+', ' ');

    /**
     * Options for the spinner
     */
    var opts = {
        lines: 17 // The number of lines to draw
        , length: 56 // The length of each line
        , width: 19 // The line thickness
        , radius: 41 // The radius of the inner circle
        , scale: 0.4 // Scales overall size of the spinner
        , corners: 1 // Corner roundness (0..1)
        , color: '#000' // #rgb or #rrggbb or array of colors
        , opacity: 0.2 // Opacity of the lines
        , rotate: 0 // The rotation offset
        , direction: 1 // 1: clockwise, -1: counterclockwise
        , speed: 0.7 // Rounds per second
        , trail: 60 // Afterglow percentage
        , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
        , zIndex: 2e9 // The z-index (defaults to 2000000000)
        , className: 'spinner' // The CSS class to assign to the spinner
        , top: '40%' // Top position relative to parent
        , shadow: false // Whether to render a shadow
        , hwaccel: false // Whether to use hardware acceleration
    };

    var target = document.getElementById('content');
    var spinner = new Spinner(opts);

    var title_text = "";        // title of the highcharts-graph -> currently none
    var subtitle_text = "";     // subtitle of the highcharts-graph -> currently none
    var graph_type = "spline";  // for more detail, see Highcharts-API-Documentation
    var x_axis_name = "Time";   // label of x_axis -> Default (when page has loaded at the beginning): Time
    var y_axis_name = "Frequencies";    // label of y_axis -> Default (when page has loaded at the beginning): Frequencies
    var resultFromServer;       // Received Data from Server. Data to Plot in Graph.
    var mySeries = [];
    var data_serie;             // Data to plot. For more detail, see Highcharts-API-Documentation   
    var selected_3d_val;

    var currentGraphNumber;
    var number_of_graphs;
    var drawingLastGraph;
    var lastGraphThereYet;
    var limit;


    // Hiding of filter-settings, that are not changeable at the beginning, because of the default-constellation of the axis-settings.
    $("#destinations_selector").hide();
    $("#timeDimension_selector").hide();
    $("#weekday_selector").hide();
    $("#dialog").hide();
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
            series: mySeries
        });
        spinner.stop();
    };


    var drawChart = function (data, name, color) {

        currentGraphNumber++;
        console.log("currentGraphNumber: ");
        console.log(currentGraphNumber);

        if (currentGraphNumber === number_of_graphs) {
            drawingLastGraph = true;
            console.log("DRAWINGLASTGRAPH = TRUE wegen number_of_graphs");
        }

        if (!lastGraphThereYet) {

            if (data.length === 0) { //wenn Rückgabe keine Daten enthält wird sie ignoriert
                console.log("NULL!");
            } else {
                //drawChart(json, line_names[i], color); //wenn Rückgabe Daten erhält wird sie nicht ignoriert und limit um eins erhöht
                console.log("DRAW!");

                //currentGraphNumber++; //erhöhen damit ich weiß wann wir das letzte mal hier sind
                limit++;
                if (limit === 15) {
                    drawingLastGraph = true;
                    console.log("DRAWINGLASTGRAPH = TRUE wegen limit == 15");
                }
                mySeries.push({name: name, data: data, color: color});
                $.redraw();


                console.log(limit);
            }

            var currentlength = data.length;
            console.log("currentlength: ");
            console.log(currentlength);
            plotDataSize += currentlength;
            console.log(plotDataSize);

            if (drawingLastGraph) { //last request
                if (plotDataSize < 1) {
                    alert("Sorry, no Data in your (3Dim) selection.");
                }
                lastGraphThereYet = true;
            }

        } else {

            console.log("Ignored call of Draw-Function!");
        }
    };

    var drawChartWithoutNames = function (data) {
        // 'series' is an array of objects with keys: 
        //     - 'name' (string)
        //     - 'data' (array)
//        var newSeriesData = {
//            name: name,
//            data: data
//        };

        // Add the new data to the series array
        mySeries.push({data: data});
        $.redraw();
        // If you want to remove old series data, you can do that here too

        // Render the chart
        //var chart = new Highcharts.Chart(options1);
    };

    $.updateDataToPlot = function () {
        //TODO Anpassen!
        data_serie = [{data: resultFromServer}]; //something like this
        $.redraw(); //always at the beginning with default values
    };


    $.redraw(); //always at the beginning with default values -> crates empty graph

    /**
     * Checks / Unchecks all destinations.
     */
    $("#check_all_destinations").change(function () {
        $("input:checkbox[name='destination']").prop('checked', $(this).prop("checked"));
    });

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
        var selected_3d = $("#3d_selector option:selected").text();
        if (x_axis_name === "Time") {
            if (selected_3d !== "Destination") {
                $("#destinations_selector").hide();
            }
            if (selected_3d !== "Airline") {
                $("#airlines_selector").hide();
            }
            $("#timeDimension_selector").show();
        } else if (x_axis_name === "Destination") {
            $("#destinations_selector").show();
            if (selected_3d !== "Airline") {
                $("#airlines_selector").hide();
            }
            if (selected_3d !== "Time") {
                $("#timeDimension_selector").hide();
                $("#weekday_selector").hide();
            }
        } else if (x_axis_name === "Airline") {
            if (selected_3d !== "Destination") {
                $("#destinations_selector").hide();
            }
            $("#airlines_selector").show();
            if (selected_3d !== "Time") {
                $("#timeDimension_selector").hide();
                $("#weekday_selector").hide();
            }
        }
        $('#3d_selector option').filter(function (i, e) {
            return $(e).text() === x_axis_name;
        }).attr("disabled", "");
    });

    /**
     * Hides or shows changeable filter-settings, depending on filter-criteria at the 3rd dimension.
     */
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
            $("#destinations_selector").hide();
        }
        selected_3d_val = $("#3d_selector option:selected").text();
        if (selected_3d_val === "Airline") {
            $("#airlines_selector").show();
        } else if (selected_3d_val === "Time") {
            $("#timeDimension_selector").show();
        } else if (selected_3d_val === "Destination") {
            $("#destinations_selector").show();
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

        mySeries = []; //clean

        plotDataSize = 0; //reset
        number_of_graphs = 0;
        currentGraphNumber = 0;
        drawingLastGraph = false;
        lastGraphThereYet = false;
        limit = 0;

        var xaxis = $("#xaxis_selector option:selected").text();        // selected filter for x-axis
        var yaxis = $("#y_qualifier option:selected").text();           // selected filter for y-axis
        var timedim = $('input[name="timeDimension"]:checked').val();   // selected timedimension
        var thirddim = $("#3d_selector option:selected").text();     // selected third dimension
        var destinations = $('input[name="destination"]:checked').map(function () { //selected destinations
            return this.value;
        }).get();
        var timerange = [$('input[name="startDate"]').val(), $('input[name="endDate"]').val()]; //selected timerange
        var airlines = $('input[name="airline"]:checked').map(function () { //selected airlines
            return this.value;
        }).get();
//        if(airlines.length === 1){
//            escape
//        }
        var weekdays = $('input[name="weekday"]:checked').map(function () { //selected weekdays
            return this.value;
        }).get();

        // Logging for testing, during development
        console.log(yaxis);
        console.log(xaxis);
        console.log(destinations);
        console.log(timerange);
        console.log(airlines);
        console.log(timedim);
        console.log(weekdays);
        console.log("Thirddim ja nein:");
        console.log(thirddim.length);
        console.log(thirddim);

        var destinationGiven = xaxis === "Destination" && destinations.length !== 0;
        var airlineGiven = xaxis === "Airline" && airlines.length !== 0;
        var timeGiven = xaxis === "Time" && timerange.length !== 0;

        if (destinationGiven || airlineGiven || timeGiven) {
            spinner.spin(target);
        }


        var thirddimAvailable = (thirddim.length !== 16);

        // sending information to querybuilder and receiving plotable data from server
        var url = "/FlySoftDemo/workarea/graphdata";

        if (!thirddimAvailable) {
            destinations.push(""); //workaround for comma-problem
            airlines.push(""); //workaround for comma-problem
            $.getJSON(url, {xaxis: escape(xaxis), yaxis: yaxis, timedim: timedim, weekdays: weekdays, thirddim: thirddim, destinations: destinations, timerange: timerange, airlines: airlines}, function (json) {
                //console.log(json);
                resultFromServer = json;
                var length = json.length;
                if (length < 1) {
                    alert("Sorry, no Data in your (2Dim) selection.");
                }
                //update axis-/labelnames for graph
                if (xaxis === "Time") {
                    x_axis_name = "Time in " + timedim;
                } else {
                    x_axis_name = xaxis;
                }
                y_axis_name = yaxis;
                drawChartWithoutNames(json);
            });
        } else {
            var dim_3 = $("#3d_selector option:selected").text();
            var line_names = [];
            if (dim_3 === "Airline") {
                number_of_graphs = airlines.length;
            } else if (selected_3d_val === "Time") {
                //TODO
                //differenciation between days/month/years
                if (timedim === "Weekdays") {
                    number_of_graphs = weekdays.length;
                }
                //TODO else if
            } else if (selected_3d_val === "Destination") {
                number_of_graphs = destinations.length;
            }
            console.log(number_of_graphs);


            //////////////////////////////////////
            //////////////////////////////////////
            //////////////////////////////////////




            if (number_of_graphs > 15) {
                $("#dialog").dialog({width: 500, height: 200});
                // weggenommen: number_of_graphs = 15;
            }


            //////////////////////////////////////
            //////////////////////////////////////
            //////////////////////////////////////


            var colors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1', '#0d233a', '#8bbc21', '#910000', '#1aadce',
                '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1', '#0d233a', '#8bbc21', '#910000', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1', '#0d233a', '#8bbc21', '#910000'];


            //////////////////////////////////////
            //////////////////////////////////////
            //////////////////////////////////////

            var i = 0;
            // limit = 0;

            while (i < number_of_graphs) {




                //if (limit >= 15) {
                //    console.log("BREAK!");
                //    break;
                //} 

                //////////////////////////////////////
                //////////////////////////////////////
                //////////////////////////////////////



                (function (i) { // protects i in an immediately called function
                    if (dim_3 === "Airline") {
                        var airline_separated = [];
                        airline_separated.push(airlines[i]);
                        line_names.push(airline_separated.toString());
                    } else if (selected_3d_val === "Time") {
                        if (timedim === "Day") {
                            //TODO
                        } else if (timedim === "Week") {
                            //TODO
                        } else if (timedim === "Year") {
                            //TODO
                        } else if (timedim === "Month") {
                            //TODO
                        } else if (timedim === "Weekdays") {
                            var weekdays_separated = [];
                            weekdays_separated.push(weekdays[i]);
                        }
                    } else if (selected_3d_val === "Destination") {
                        var destination_separated = [];
                        destination_separated.push(destinations[i]);
                        line_names.push(destination_separated.toString());
                    }

                    var json_airline = airlines;
                    var json_destination = destinations;
                    var json_weekdays = weekdays;
                    if (dim_3 === "Airline") {
                        json_airline = airline_separated;
                    } else if (dim_3 === "Time") {
                        //TODO
                        if (timedim === "Day") {
                            //TODO
                        } else if (timedim === "Week") {
                            //TODO
                        } else if (timedim === "Year") {
                            //TODO
                        } else if (timedim === "Month") {
                            //TODO
                        } else if (timedim === "Weekdays") {
                            json_weekdays = weekdays_separated;
                        }
                    } else if (dim_3 === "Destination") {
                        json_destination = destination_separated;
                    }
                    if (json_destination.length === 1) {
                        json_destination.push(""); //workaround for commaproblem
                    }
                    if (json_airline.length === 1) {
                        json_airline.push(""); //workaround for commaproblem
                    }

                    $.getJSON(url, {xaxis: escape(xaxis), yaxis: yaxis, timedim: timedim, weekdays: json_weekdays, thirddim: thirddim, destinations: json_destination, timerange: timerange, airlines: json_airline}, function (json) {

                        //update axis-/labelnames for graph
                        if (xaxis === "Time") {
                            x_axis_name = "Time in " + timedim;
                        } else {
                            x_axis_name = xaxis;
                        }

                        y_axis_name = yaxis;

                        var color = colors.pop();


                        //////////////////////////////////////
                        //////////////////////////////////////
                        //////////////////////////////////////

                        console.log(json);
                        //if (json.length === 0) { //wenn Rückgabe keine Daten enthält wird sie ignoriert
                        //    console.log("NULL!");
                        //} else {
                        drawChart(json, line_names[i], color); //wenn Rückgabe Daten erhält wird sie nicht ignoriert und limit um eins erhöht
                        console.log("DRAW!");
                        //    limit++;
                        //    console.log(limit);
                        //}


                        //////////////////////////////////////
                        //////////////////////////////////////
                        //////////////////////////////////////

                    });

                })(i);
                i++;
            }
        }
    });
});
