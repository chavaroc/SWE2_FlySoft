/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

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
 *
 * @author Betina Hientz
 */
@Entity
@Table(name = "flightendpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flightendpoint.findAll", query = "SELECT f FROM Flightendpoint f"),
    @NamedQuery(name = "Flightendpoint.findByFlightendpointId", query = "SELECT f FROM Flightendpoint f WHERE f.flightendpointId = :flightendpointId"),
    @NamedQuery(name = "Flightendpoint.findByOriginairportshortname", query = "SELECT f FROM Flightendpoint f WHERE f.originairportshortname = :originairportshortname"),
    @NamedQuery(name = "Flightendpoint.findByDepaturetime", query = "SELECT f FROM Flightendpoint f WHERE f.depaturetime = :depaturetime"),
    @NamedQuery(name = "Flightendpoint.findByDepaturedelay", query = "SELECT f FROM Flightendpoint f WHERE f.depaturedelay = :depaturedelay"),
    @NamedQuery(name = "Flightendpoint.findByDestairportshortname", query = "SELECT f FROM Flightendpoint f WHERE f.destairportshortname = :destairportshortname"),
    @NamedQuery(name = "Flightendpoint.findByArrivaltime", query = "SELECT f FROM Flightendpoint f WHERE f.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Flightendpoint.findByArrivaldelay", query = "SELECT f FROM Flightendpoint f WHERE f.arrivaldelay = :arrivaldelay")})
public class Flightendpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flightendpoint_id")
    private Integer flightendpointId;
    @Column(name = "originairportshortname")
    private String originairportshortname;
    @Basic(optional = false)
    @Column(name = "depaturetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depaturetime;
    @Basic(optional = false)
    @Column(name = "depaturedelay")
    private double depaturedelay;
    @Column(name = "destairportshortname")
    private String destairportshortname;
    @Basic(optional = false)
    @Column(name = "arrivaltime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivaltime;
    @Basic(optional = false)
    @Column(name = "arrivaldelay")
    private double arrivaldelay;

    public Flightendpoint() {
    }

    public Flightendpoint(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    public Flightendpoint(Integer flightendpointId, Date depaturetime, double depaturedelay, Date arrivaltime, double arrivaldelay) {
        this.flightendpointId = flightendpointId;
        this.depaturetime = depaturetime;
        this.depaturedelay = depaturedelay;
        this.arrivaltime = arrivaltime;
        this.arrivaldelay = arrivaldelay;
    }

    public Integer getFlightendpointId() {
        return flightendpointId;
    }

    public void setFlightendpointId(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    public String getOriginairportshortname() {
        return originairportshortname;
    }

    public void setOriginairportshortname(String originairportshortname) {
        this.originairportshortname = originairportshortname;
    }

    public Date getDepaturetime() {
        return depaturetime;
    }

    public void setDepaturetime(Date depaturetime) {
        this.depaturetime = depaturetime;
    }

    public double getDepaturedelay() {
        return depaturedelay;
    }

    public void setDepaturedelay(double depaturedelay) {
        this.depaturedelay = depaturedelay;
    }

    public String getDestairportshortname() {
        return destairportshortname;
    }

    public void setDestairportshortname(String destairportshortname) {
        this.destairportshortname = destairportshortname;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public double getArrivaldelay() {
        return arrivaldelay;
    }

    public void setArrivaldelay(double arrivaldelay) {
        this.arrivaldelay = arrivaldelay;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightendpointId != null ? flightendpointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flightendpoint)) {
            return false;
        }
        Flightendpoint other = (Flightendpoint) object;
        if ((this.flightendpointId == null && other.flightendpointId != null) || (this.flightendpointId != null && !this.flightendpointId.equals(other.flightendpointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Flightendpoint[ flightendpointId=" + flightendpointId + " ]";
    }
    
}
