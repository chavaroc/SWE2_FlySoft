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
import org.eclipse.persistence.annotations.ExistenceChecking;
import org.eclipse.persistence.annotations.ExistenceType;

/**
 * Class who represents the table flightendpoint in the database with all attributes.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "flightendpoint")
@XmlRootElement
@ExistenceChecking(ExistenceType.ASSUME_NON_EXISTENCE)
@NamedQueries({
    @NamedQuery(name = "FlightEndPoint.findAll", query = "SELECT f FROM FlightEndPoint f"),
    @NamedQuery(name = "FlightEndPoint.findByFlightEndPointId",
        query = "SELECT f FROM FlightEndPoint f WHERE f.flightEndPointId = :flightEndPointId"),
    @NamedQuery(name = "FlightEndPoint.findByOriginAirportShortName",
        query = "SELECT f FROM FlightEndPoint f WHERE f.originAirportShortName = :originAirportShortName"),
    @NamedQuery(name = "FlightEndPoint.findByDepartureTime",
        query = "SELECT f FROM FlightEndPoint f WHERE f.departureTime = :departureTime"),
    @NamedQuery(name = "FlightEndPoint.findByDepartureDelay",
        query = "SELECT f FROM FlightEndPoint f WHERE f.departureDelay = :departureDelay"),
    @NamedQuery(name = "FlightEndPoint.findByDestAirportShortName",
        query = "SELECT f FROM FlightEndPoint f WHERE f.destAirportShortName = :destAirportShortName"),
    @NamedQuery(name = "FlightEndPoint.findByArrivalTime",
        query = "SELECT f FROM FlightEndPoint f WHERE f.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "FlightEndPoint.findByArrivalDelay",
        query = "SELECT f FROM FlightEndPoint f WHERE f.arrivalDelay = :arrivalDelay")})
public class FlightEndPoint implements IFlightEndPoints, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flightendpoint_id")
    private Integer flightEndPointId;
    @Column(name = "originairportshortname")
    private String originAirportShortName;
    @Basic(optional = false)
    @Column(name = "departuretime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Basic(optional = false)
    @Column(name = "departuredelay")
    private double departureDelay;
    @Column(name = "destairportshortname")
    private String destAirportShortName;
    @Basic(optional = false)
    @Column(name = "arrivaltime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @Basic(optional = false)
    @Column(name = "arrivaldelay")
    private double arrivalDelay;

    public FlightEndPoint() {
    }

    @Override
    public Integer getFlightEndPointId() {
        return flightEndPointId;
    }

    @Override
    public void setFlightEndPointId(Integer flightEndPointId) {
        this.flightEndPointId = flightEndPointId;
    }

    @Override
    public String getOriginAirportShortName() {
        return originAirportShortName;
    }

    @Override
    public void setOriginAirportShortName(String originAirportShortName) {
        this.originAirportShortName = originAirportShortName;
    }

    @Override
    public Date getDepartureTime() {
        return departureTime;
    }

    @Override
    public void setDepartureTime(Date depatureTime) {
        this.departureTime = depatureTime;
    }

    @Override
    public double getDepartureDelay() {
        return departureDelay;
    }

    @Override
    public void setDepartureDelay(double depatureDelay) {
        this.departureDelay = depatureDelay;
    }

    @Override
    public String getDestAirportShortName() {
        return destAirportShortName;
    }

    @Override
    public void setDestAirportShortName(String destAirportShortName) {
        this.destAirportShortName = destAirportShortName;
    }

    @Override
    public Date getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public double getArrivalDelay() {
        return arrivalDelay;
    }

    @Override
    public void setArrivalDelay(double arrivalDelay) {
        this.arrivalDelay = arrivalDelay;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightEndPointId != null ? flightEndPointId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlightEndPoint)) {
            return false;
        }
        FlightEndPoint other = (FlightEndPoint) object;
        if ((this.flightEndPointId == null && other.flightEndPointId != null)
            || (this.flightEndPointId != null && !this.flightEndPointId.equals(other.flightEndPointId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.FlightEndPoint[ flightEndPointId=" + flightEndPointId + " ]";
    }    
}
