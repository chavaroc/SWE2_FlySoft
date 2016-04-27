/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.Entity;

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
@Table(name = "flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f"),
    @NamedQuery(name = "Flight.findByFlightId", query = "SELECT f FROM Flight f WHERE f.flightId = :flightId"),
    @NamedQuery(name = "Flight.findByPassengercount", query = "SELECT f FROM Flight f WHERE f.passengercount = :passengercount"),
    @NamedQuery(name = "Flight.findByCancelled", query = "SELECT f FROM Flight f WHERE f.cancelled = :cancelled"),
    @NamedQuery(name = "Flight.findByAirlineId", query = "SELECT f FROM Flight f WHERE f.airlineId = :airlineId"),
    @NamedQuery(name = "Flight.findByFlightendpointId", query = "SELECT f FROM Flight f WHERE f.flightendpointId = :flightendpointId"),
    @NamedQuery(name = "Flight.findByFlightendpointarrivalId", query = "SELECT f FROM Flight f WHERE f.flightendpointarrivalId = :flightendpointarrivalId")})
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flight_id")
    private Integer flightId;
    
    @Basic(optional = false)
    @Column(name = "passengercount")
    private int passengercount;
    
    @Basic(optional = false)
    @Column(name = "cancelled")
    private Boolean cancelled;
    
    @Column(name = "airline_id")
    private Integer airlineId;
    
    @Column(name = "flightendpoint_id")
    private Integer flightendpointId;
    
    @Column(name = "flightendpointarrival_id")
    private Integer flightendpointarrivalId;

    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Flight(Integer flightId, int passengercount, Boolean cancelled) {
        this.flightId = flightId;
        this.passengercount = passengercount;
        this.cancelled = cancelled;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public int getPassengercount() {
        return passengercount;
    }

    public void setPassengercount(int passengercount) {
        this.passengercount = passengercount;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public Integer getFlightendpointId() {
        return flightendpointId;
    }

    public void setFlightendpointId(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    public Integer getFlightendpointarrivalId() {
        return flightendpointarrivalId;
    }

    public void setFlightendpointarrivalId(Integer flightendpointarrivalId) {
        this.flightendpointarrivalId = flightendpointarrivalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.Entities.Flight[ flightId=" + flightId + " ]";
    }
    
}
