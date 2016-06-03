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
 * Class who represents the table monthlystat in the database with all attributes.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "monthlystat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonthlyStat.findAll", query = "SELECT m FROM MonthlyStat m"),
    @NamedQuery(name = "MonthlyStat.findByMonthlyStatId", query = "SELECT m FROM MonthlyStat m WHERE m.monthlyStatId = :monthlyStatId"),
    @NamedQuery(name = "MonthlyStat.findByYearMonth", query = "SELECT m FROM MonthlyStat m WHERE m.yearMonth = :yearMonth"),
    @NamedQuery(name = "MonthlyStat.findByOrginAirportSn", query = "SELECT m FROM MonthlyStat m WHERE m.orginAirportSn = :orginAirportSn"),
    @NamedQuery(name = "MonthlyStat.findByDestAirportSn", query = "SELECT m FROM MonthlyStat m WHERE m.destAirportSn = :destAirportSn"),
    @NamedQuery(name = "MonthlyStat.findByAirlineId", query = "SELECT m FROM MonthlyStat m WHERE m.airlineId = :airlineId")})
public class MonthlyStat implements Serializable, IMonthlyStat {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monthlystat_id")
    private Integer monthlyStatId;
    @Basic(optional = false)
    @Column(name = "yearmonth")
    @Temporal(TemporalType.DATE)
    private Date yearMonth;
    @Basic(optional = false)
    @Column(name = "orginairportsn")
    private String orginAirportSn;
    @Basic(optional = false)
    @Column(name = "destairportsn")
    private String destAirportSn;
    @Column(name = "airline_id")
    private Integer airlineId;
    @Basic(optional = false)
    @Column(name = "passengercount")
    private Integer passengerCount;
    
    @Transient
    private int monthMon;
    @Transient
    private int yearMon;
    
    public MonthlyStat() {
        this(-1);
    }

    public MonthlyStat(Integer monthlystatId) {
        this(monthlystatId, new Date(),"","",-1);
    }

    public MonthlyStat(Integer monthlystatId, Date yearmonth, String orginairportsn,
            String destairportsn, Integer passengercount) {
        this.monthlyStatId = monthlystatId;
        this.yearMonth = yearmonth;
        this.orginAirportSn = orginairportsn;
        this.passengerCount = passengercount;
        this.destAirportSn = destairportsn;
    }
    
    @Override
    public String getDestAirportShortName() {
        return destAirportSn;
    }

    @Override
    public String getOriginAirportShortName() {
        return orginAirportSn;
    }

    @Override
    public int getPassengerCount() {
        return passengerCount;
    }

    @Override
    public Date getYearMonth() {
        return yearMonth;
    }

    @Override
    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    @Override
    public void setDestAirportShortName(String destination) {
        destAirportSn = destination;
   }

    @Override
    public void setOriginAirportShortName(String origin) {
        orginAirportSn = origin;
    }

    @Override
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Override
    public int getAirlineId() {   
        return airlineId;
    }

    @Override
    public int getMonthlyStatId() {
        return monthlyStatId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monthlyStatId != null ? monthlyStatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonthlyStat)) {
            return false;
        }
        MonthlyStat other = (MonthlyStat) object;
        if ((this.monthlyStatId == null && other.monthlyStatId != null) || (this.monthlyStatId != null && !this.monthlyStatId.equals(other.monthlyStatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.MonthlyStat[ monthlyStatId=" + monthlyStatId + " ]";
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
        try {
        Calendar calendar = Calendar.getInstance();
        String monthString;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM", Locale.US);
        calendar.setTime(yearMonth);
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
            if(isyear){  
                this.yearMonth = (Date)dateFormat.parse(value+"/"+
                monthString);
            }
            else{
             this.yearMonth = (Date)dateFormat.parse(calendar.get(Calendar.YEAR)+"/"+
                monthString); 
            }
        } catch (Exception e) {System.err.println(e);
        }
    }
}
