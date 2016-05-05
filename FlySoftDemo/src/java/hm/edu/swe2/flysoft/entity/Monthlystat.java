/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
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
public class Monthlystat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monthlystat_id")
    private Integer monthlystatId;
    @Basic(optional = false)
    @Column(name = "yearmonth")
    private String yearmonth;
    @Basic(optional = false)
    @Column(name = "orginairportsn")
    private String orginairportsn;
    @Basic(optional = false)
    @Column(name = "destairportsn")
    private String destairportsn;
    @Column(name = "airline_id")
    private Integer airlineId;

    public Monthlystat() {
    }

    public Monthlystat(Integer monthlystatId) {
        this.monthlystatId = monthlystatId;
    }

    public Monthlystat(Integer monthlystatId, String yearmonth, String orginairportsn, String destairportsn) {
        this.monthlystatId = monthlystatId;
        this.yearmonth = yearmonth;
        this.orginairportsn = orginairportsn;
        this.destairportsn = destairportsn;
    }

    public Integer getMonthlystatId() {
        return monthlystatId;
    }

    public void setMonthlystatId(Integer monthlystatId) {
        this.monthlystatId = monthlystatId;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public String getOrginairportsn() {
        return orginairportsn;
    }

    public void setOrginairportsn(String orginairportsn) {
        this.orginairportsn = orginairportsn;
    }

    public String getDestairportsn() {
        return destairportsn;
    }

    public void setDestairportsn(String destairportsn) {
        this.destairportsn = destairportsn;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
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
    
}
