package hm.edu.swe2.flysoft;

import java.util.Date;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class FilterSetting {
    
    private String xaxis;
    private String yaxis;
    private String[] airlines;
    
    private Date dateFrom;
    private Date dateTo;

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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
