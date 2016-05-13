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
    var selected_x_val;
    var data_serie;
    var x_axis_unit;


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
                type: 'number',
                dateTimeLabelFormats: {// don't display the dummy year
                    month: '%e. %b',
                    year: '%b'
                },
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
                headerFormat: '<b>{series.name}</b><br>',
                pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
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
    
    
    $("#x_qualifier").change(function () {
        $('#3d_qualifier option').filter(function (i, e) {
            return $(e).text() === x_axis_name;
        }).removeAttr("disabled");
        x_axis_name = $("#x_qualifier option:selected").text();
        selected_x_val = $("#x_qualifier option:selected").val();
        $.redraw();
        $('#3d_qualifier option').filter(function (i, e) {
            return $(e).text() === x_axis_name;
        }).attr("disabled", "");

    });

    $("#y_qualifier").change(function () {
        y_axis_name = $("#y_qualifier option:selected").text();
        ;
        $.redraw();
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

    // Request data from server for current filter settings
    // Attension: name of 'timedim' should be equal to sql day/week/month functions
    $("#submit_button").click(function () {
        var xaxis = $("#xaxis option:selected").text(); //"Time";
        var yaxis = $("#y_qualifier option:selected").text();
        var timedim = $("input[name=timeDimension]:checked").val();
        var thirddim = $("#thirdDimension option:selected").text();
        var destinations = "Las Vegas, NV";
        var timerange = ["01.01.2015", "31.12.2015"];
        var airlines = ["all"];
        
        if(xaxis === "Time"){
            if(timedim === "Week"){
                //TODO change axis title and axis scale / scale description
                x_axis_unit = "week"; //noch schauen was ich damit genaue machen kann
            }
        }
        console.log(destinations);
        console.log(yaxis);
        console.log(xaxis);

         

        var url = "/FlySoftDemo/workarea/graphdata";
        $.getJSON(url, {xaxis: escape(xaxis), yaxis: yaxis, timedim: timedim, thirddim: thirddim, destinations: destinations, timerange: timerange, airlines: airlines}, function (json) {
            console.log(json);
            resultFromServer = json;
            //update label-names for graph
            x_axis_name = xaxis;
            y_axis_name = yaxis;
            $.updateDataToPlot(); //Zu plottende Daten aktualisieren neu zeichnen lassen
        });
        
        

    });

});
