/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IMonthlyStat;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Betina Hientz
 */
@Entity
@Table(name = "monthlystat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monthlystat.findAll", query = "SELECT m FROM Monthlystat m"),
    @NamedQuery(name = "Monthlystat.findByMonthlystatId", query = "SELECT m FROM Monthlystat m WHERE m.monthlystatId = :monthlystatId"),
    @NamedQuery(name = "Monthlystat.findByYearmonth", query = "SELECT m FROM Monthlystat m WHERE m.yearmonth = :yearmonth"),
    @NamedQuery(name = "Monthlystat.findByOrginairportsn", query = "SELECT m FROM Monthlystat m WHERE m.orginairportsn = :orginairportsn"),
    @NamedQuery(name = "Monthlystat.findByDestairportsn", query = "SELECT m FROM Monthlystat m WHERE m.destairportsn = :destairportsn"),
    @NamedQuery(name = "Monthlystat.findByAirlineId", query = "SELECT m FROM Monthlystat m WHERE m.airlineId = :airlineId")})
public class Monthlystat implements Serializable, IMonthlyStat {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monthlystat_id")
    private Integer monthlystatId;
    @Basic(optional = false)
    @Column(name = "yearmonth")
    @Temporal(TemporalType.DATE)
    private Date yearmonth;
    @Basic(optional = false)
    @Column(name = "orginairportsn")
    private String orginairportsn;
    @Basic(optional = false)
    @Column(name = "destairportsn")
    private String destairportsn;
    @Column(name = "airline_id")
    private Integer airlineId;
    @Basic(optional = false)
    @Column(name = "passengercount")
    private Integer passengercount;
    
    @Transient
    private int monthMon;
    @Transient
    private int yearMon;
    
    public Monthlystat() {
        this(-1);
    }

    public Monthlystat(Integer monthlystatId) {
        this(monthlystatId, new Date(0),"","",-1);
    }

    public Monthlystat(Integer monthlystatId, Date yearmonth, String orginairportsn,
            String destairportsn, Integer passengercount) {
        this.monthlystatId = monthlystatId;
        this.yearmonth = yearmonth;
        this.orginairportsn = orginairportsn;
        this.passengercount = passengercount;
        this.destairportsn = destairportsn;
        
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monthlystatId != null ? monthlystatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monthlystat)) {
            return false;
        }
        Monthlystat other = (Monthlystat) object;
        if ((this.monthlystatId == null && other.monthlystatId != null) || (this.monthlystatId != null && !this.monthlystatId.equals(other.monthlystatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Monthlystat[ monthlystatId=" + monthlystatId + " ]";
    }

    @Override
    public String getDestAirportShortName() {
        return destairportsn;
    }

    @Override
    public String getOriginAirportShortName() {
        return orginairportsn;
    }

    @Override
    public int getPassengerCount() {
        return passengercount;
    }

    @Override
    public Date getYearMonth() {
          
        return yearmonth;
    }

    @Override
    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    @Override
    public void setDestAirportShortName(String destination) {
        destairportsn = destination;
   }

    @Override
    public void setOriginAirportShortName(String origin) {
        orginairportsn = origin;
    }

    @Override
    public void setPassengerCount(int passengerCount) {
        this.passengercount = passengerCount;
    }

    @Override
    public int getAirlineId() {   
        return airlineId;
    }

    @Override
    public int getMonthlyStatId() {
        return monthlystatId;
    }

    @Override
    public void setMonth(int month) {
        this.monthMon = month;
        adjustYearMonth(false, month);
    }

    @Override
    public void setYear(int year) {
        this.yearMon = year;
        adjustYearMonth(true, year);
    }

    @Override
    public int getMonth() {
        return monthMon;
    }

    @Override
    public int getYear() {
        return yearMon;
    }
    
    private void adjustYearMonth(boolean isyear, int value){
      
        
        Calendar calendar = Calendar.getInstance();
        String monthString;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.US);
        calendar.setTime(yearmonth);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if(!isyear){
            month = value;
        }
        else{
            month = calendar.get(Calendar.MONTH);
        }
        if(month <= 9){
            monthString = "0"+month;
        }
        else{
            monthString = Integer.toString(month);
        }
        try {
            if(isyear){  
             this.yearmonth = dateFormat.parse(value+"-"+
                monthString);
            }
            else{
             this.yearmonth = dateFormat.parse(calendar.get(Calendar.YEAR)+"-"+
                monthString);   
            }
        } catch (Exception e) {System.err.println(e);
        }
    }
}
