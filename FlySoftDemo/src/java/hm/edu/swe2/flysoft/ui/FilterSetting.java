/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.ui;

import java.util.Date;

/**
 *
 * @author Philipp Chavaroche
 * @version
 */
public class FilterSetting {

    private String xaxis;
    private String yaxis;
    private String thirdDimension;
    private String[] airlines;
    private String[] airlinesnew;
    //private String[] origins;
    private String[] destinations;
    private String timeDimension; //day, week or year
    private Date timeFrom;
    private Date timeTo;

    public String getXaxis() {
        return xaxis;
    }

    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    public String getYaxis() {
        return yaxis;
    }

    public void setYaxis(String yaxis) {
        this.yaxis = yaxis;
    }

    public String[] getAirlines() {
        return airlines;
    }

    public void setAirlines(String[] arilines) {
        this.airlines = arilines;
    }

    public String[] getDestinations() {
        return destinations;
    }

    public String getTimeDimension() {
        return timeDimension;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setDestinations(String[] destinations) {
        this.destinations = destinations;
    }

    public void setTimeDimension(String timeDimension) {
        this.timeDimension = timeDimension;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getThirdDimension() {
        return thirdDimension;
    }

    public void setThirdDimension(String thirdDimension) {
        this.thirdDimension = thirdDimension;
    }

    public String[] getAirlinesnew() {
        return airlinesnew;
    }

    public void setAirlinesnew(String[] airlinesnew) {
        this.airlinesnew = airlinesnew;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }
    
    
    
}
