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
    var selected_x_val;

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
                type: 'datetime',
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
            series: [
                {
                    name: 'Winter 2013-2014',
                    data: [
                        [Date.UTC(1970, 9, 29), 0],
                        [Date.UTC(1970, 10, 9), 0.4],
                        [Date.UTC(1970, 11, 1), 0.25],
                        [Date.UTC(1971, 0, 1), 1.66],
                        [Date.UTC(1971, 0, 10), 1.8],
                        [Date.UTC(1971, 1, 19), 1.76],
                        [Date.UTC(1971, 2, 25), 2.62],
                        [Date.UTC(1971, 3, 19), 2.41],
                        [Date.UTC(1971, 3, 30), 2.05],
                        [Date.UTC(1971, 4, 14), 1.7],
                        [Date.UTC(1971, 4, 24), 1.1],
                        [Date.UTC(1971, 5, 10), 0]
                    ]
                }, {
                    name: 'Winter 2016-2017',
                    data: [
                        [Date.UTC(1970, 9, 29), 3],
                        [Date.UTC(1970, 10, 9), 0.4],
                        [Date.UTC(1970, 11, 1), 0.25],
                        [Date.UTC(1971, 0, 1), 2.66],
                        [Date.UTC(1971, 0, 10), 1.8],
                        [Date.UTC(1971, 1, 19), 0.76],
                        [Date.UTC(1971, 2, 25), 2.62],
                        [Date.UTC(1971, 3, 19), 2.41],
                        [Date.UTC(1971, 3, 30), 1.05],
                        [Date.UTC(1971, 4, 14), 2.7],
                        [Date.UTC(1971, 4, 24), 1.1],
                        [Date.UTC(1971, 5, 10), 1]
                    ]
                }]
        });
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

});