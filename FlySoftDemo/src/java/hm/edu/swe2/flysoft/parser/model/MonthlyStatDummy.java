package hm.edu.swe2.flysoft.parser.model;

import hm.edu.swe2.flysoft.interfaces.IMonthlyStat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Represents a dummy class, that is used by the csv parser 
 * for the segement table.
 * @author Philipp Chavaroche
 * @version 02.05.2016
 */
public class MonthlyStatDummy implements IMonthlyStat {
    
    private int airlineId;
    private String origin;
    private String destination;
    private int year;
    private int month;
    private int passengerCount;

    @Override
    public int getAirlineId() {
        return airlineId;
    }

    @Override
    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    @Override
    public String getOriginAirportShortName() {
        return origin;
    }

    @Override
    public void setOriginAirportShortName(String origin) {
        this.origin = origin;
    }

    @Override
    public String getDestAirportShortName() {
        return destination;
    }

    @Override
    public void setDestAirportShortName(String destination) {
        this.destination = destination;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public int getPassengerCount() {
        return passengerCount;
    }

    @Override
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Override
    public Date getYearMonth() {
        Date yearMonthdate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            yearMonthdate = dateFormat.parse(getYear() + "-" + getMonth() + "-01");
        } catch (ParseException ex) {
            yearMonthdate = null;
        }
        return yearMonthdate;
    }

    @Override
    public int getMonthlyStatId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
