package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IMonthlyStat;
import java.io.Serializable;
import java.util.Date;
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

    public MonthlyStat() {
    }

    public MonthlyStat(Integer monthlyStatId) {
        this.monthlyStatId = monthlyStatId;
    }

    public MonthlyStat(Integer monthlyStatId, Date yearMonth, String orginAirportSn,
            String destAirportSn, Integer passengerCount) {
        this.monthlyStatId = monthlyStatId;
        this.yearMonth = yearMonth;
        this.orginAirportSn = orginAirportSn;
        this.passengerCount = passengerCount;
        this.destAirportSn = destAirportSn;
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
}
