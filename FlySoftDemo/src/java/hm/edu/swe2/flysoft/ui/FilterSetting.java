package hm.edu.swe2.flysoft.ui;

import java.util.Date;

/**
 * Represents a choosen filter setting.
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
public class FilterSetting {

    private String xaxis;
    private String yaxis;
    private String thirdDimension;
    private String[] airlines;
    private String[] airlinesnew;
    private String[] origins;
    private String[] destinations;
    private String timeDimension; //day, week, weekdays or year
    private Date timeFrom;
    private Date timeTo;

    /**
     * Get the x-axis of the setting.
     * @return The name of the x-axis.
     */
    public String getXaxis() {
        return xaxis;
    }

    /**
     * Set the x-axis of the setting.
     * @param xaxis 
     */
    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    /**
     * Get the y-axis of the setting.
     * @return The name of the y-axis.
     */
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

    public String[] getOrigins() {
        return origins;
    }

    public void setOrigins(String[] origins) {
        this.origins = origins;
    }  
    
}
