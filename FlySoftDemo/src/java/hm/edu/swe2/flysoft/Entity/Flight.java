/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IFlight;
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
    @NamedQuery(name = "Flight.findByCancelled", query = "SELECT f FROM Flight f WHERE f.cancelled = :cancelled"),
    @NamedQuery(name = "Flight.findByFlightendpointId", query = "SELECT f FROM Flight f WHERE f.flightendpointId = :flightendpointId"),
    @NamedQuery(name = "Flight.findByAirlineId", query = "SELECT f FROM Flight f WHERE f.airlineId = :airlineId")})
public class Flight implements Serializable, IFlight {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flight_id")
    private Integer flightId;
    @Basic(optional = false)
    @Column(name = "cancelled")
    private boolean cancelled;
    @Column(name = "flightendpoint_id")
    private Integer flightendpointId;
    @Column(name = "airline_id")
    private Integer airlineId;

    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Flight(Integer flightId, boolean cancelled) {
        this.flightId = flightId;
        this.cancelled = cancelled;
    }

    @Override
    public Integer getFlightId() {
        return flightId;
    }

    @Override
    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    @Override
    public boolean getCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getFlightendpointId() {
        return flightendpointId;
    }

    public void setFlightendpointId(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    @Override
    public Integer getAirlineId() {
        return airlineId;
    }

    @Override
    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
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
        return "hm.edu.swe2.flysoft.entity.Flight[ flightId=" + flightId + " ]";
    }
    
}
