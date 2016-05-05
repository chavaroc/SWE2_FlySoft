/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
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
    @NamedQuery(name = "Flightendpoint.findByDeparturetime", query = "SELECT f FROM Flightendpoint f WHERE f.departuretime = :departuretime"),
    @NamedQuery(name = "Flightendpoint.findByDeparturedelay", query = "SELECT f FROM Flightendpoint f WHERE f.departuredelay = :departuredelay"),
    @NamedQuery(name = "Flightendpoint.findByDestairportshortname", query = "SELECT f FROM Flightendpoint f WHERE f.destairportshortname = :destairportshortname"),
    @NamedQuery(name = "Flightendpoint.findByArrivaltime", query = "SELECT f FROM Flightendpoint f WHERE f.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Flightendpoint.findByArrivaldelay", query = "SELECT f FROM Flightendpoint f WHERE f.arrivaldelay = :arrivaldelay")})
public class Flightendpoint implements IFlightEndPoints, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flightendpoint_id")
    private Integer flightendpointId;
    @Column(name = "originairportshortname")
    private String originairportshortname;
    @Basic(optional = false)
    @Column(name = "departuretime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departuretime;
    @Basic(optional = false)
    @Column(name = "departuredelay")
    private double departuredelay;
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

    public Flightendpoint(Integer flightendpointId, Date departuretime, double departuredelay, Date arrivaltime, double arrivaldelay) {
        this.flightendpointId = flightendpointId;
        this.departuretime = departuretime;
        this.departuredelay = departuredelay;
        this.arrivaltime = arrivaltime;
        this.arrivaldelay = arrivaldelay;
    }

    public Integer getFlightendpointId() {
        return flightendpointId;
    }

    public void setFlightendpointId(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    @Override
    public String getOriginAirportShortName() {
        return originairportshortname;
    }

    @Override
    public void setOriginAirportShortName(String originairportshortname) {
        this.originairportshortname = originairportshortname;
    }

    @Override
    public Date getDepartureTime() {
        return departuretime;
    }

    @Override
    public void setDepartureTime(Date depaturetime) {
        this.departuretime = depaturetime;
    }

    @Override
    public double getDeparturedelay() {
        return departuredelay;
    }

    @Override
    public void setDeparturedelay(double depaturedelay) {
        this.departuredelay = depaturedelay;
    }

    @Override
    public String getDestAirportShortName() {
        return destairportshortname;
    }

    @Override
    public void setDestAirportShortName(String destairportshortname) {
        this.destairportshortname = destairportshortname;
    }

    @Override
    public Date getArrivalTime() {
        return arrivaltime;
    }

    @Override
    public void setArrivalTime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    @Override
    public double getArrivalDelay() {
        return arrivaldelay;
    }

    @Override
    public void setArrivalDelay(double arrivaldelay) {
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
