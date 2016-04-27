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
@Table(name = "flightendpointtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flightendpointtype.findAll", query = "SELECT f FROM Flightendpointtype f"),
    @NamedQuery(name = "Flightendpointtype.findByFlightendpointtypeId", query = "SELECT f FROM Flightendpointtype f WHERE f.flightendpointtypeId = :flightendpointtypeId"),
    @NamedQuery(name = "Flightendpointtype.findByName", query = "SELECT f FROM Flightendpointtype f WHERE f.name = :name")})
public class Flightendpointtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flightendpointtype_id")
    private Integer flightendpointtypeId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Flightendpointtype() {
    }

    public Flightendpointtype(Integer flightendpointtypeId) {
        this.flightendpointtypeId = flightendpointtypeId;
    }

    public Flightendpointtype(Integer flightendpointtypeId, String name) {
        this.flightendpointtypeId = flightendpointtypeId;
        this.name = name;
    }

    public Integer getFlightendpointtypeId() {
        return flightendpointtypeId;
    }

    public void setFlightendpointtypeId(Integer flightendpointtypeId) {
        this.flightendpointtypeId = flightendpointtypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightendpointtypeId != null ? flightendpointtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flightendpointtype)) {
            return false;
        }
        Flightendpointtype other = (Flightendpointtype) object;
        if ((this.flightendpointtypeId == null && other.flightendpointtypeId != null) || (this.flightendpointtypeId != null && !this.flightendpointtypeId.equals(other.flightendpointtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.Entities.Flightendpointtype[ flightendpointtypeId=" + flightendpointtypeId + " ]";
    }
    
}
