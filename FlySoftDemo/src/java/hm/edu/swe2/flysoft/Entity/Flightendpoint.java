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
@Table(name = "flightendpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flightendpoint.findAll", query = "SELECT f FROM Flightendpoint f"),
    @NamedQuery(name = "Flightendpoint.findByFlightendpointId", query = "SELECT f FROM Flightendpoint f WHERE f.flightendpointId = :flightendpointId"),
    @NamedQuery(name = "Flightendpoint.findByActualtime", query = "SELECT f FROM Flightendpoint f WHERE f.actualtime = :actualtime"),
    @NamedQuery(name = "Flightendpoint.findByDelay", query = "SELECT f FROM Flightendpoint f WHERE f.delay = :delay"),
    @NamedQuery(name = "Flightendpoint.findByCityId", query = "SELECT f FROM Flightendpoint f WHERE f.cityId = :cityId"),
    @NamedQuery(name = "Flightendpoint.findByFlightendpointtypeId", query = "SELECT f FROM Flightendpoint f WHERE f.flightendpointtypeId = :flightendpointtypeId")})
public class Flightendpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flightendpoint_id")
    private Integer flightendpointId;
    @Basic(optional = false)
    @Column(name = "actualtime")
    private String actualtime;
    @Basic(optional = false)
    @Column(name = "delay")
    private String delay;
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "flightendpointtype_id")
    private Integer flightendpointtypeId;

    public Flightendpoint() {
    }

    public Flightendpoint(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    public Flightendpoint(Integer flightendpointId, String actualtime, String delay) {
        this.flightendpointId = flightendpointId;
        this.actualtime = actualtime;
        this.delay = delay;
    }

    public Integer getFlightendpointId() {
        return flightendpointId;
    }

    public void setFlightendpointId(Integer flightendpointId) {
        this.flightendpointId = flightendpointId;
    }

    public String getActualtime() {
        return actualtime;
    }

    public void setActualtime(String actualtime) {
        this.actualtime = actualtime;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getFlightendpointtypeId() {
        return flightendpointtypeId;
    }

    public void setFlightendpointtypeId(Integer flightendpointtypeId) {
        this.flightendpointtypeId = flightendpointtypeId;
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
        return "hm.edu.swe2.flysoft.Entities.Flightendpoint[ flightendpointId=" + flightendpointId + " ]";
    }
    
}
