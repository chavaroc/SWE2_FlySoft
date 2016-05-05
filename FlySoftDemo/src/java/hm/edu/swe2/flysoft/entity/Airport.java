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
@Table(name = "airport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a"),
    @NamedQuery(name = "Airport.findByShortname", query = "SELECT a FROM Airport a WHERE a.shortname = :shortname"),
    @NamedQuery(name = "Airport.findByAirportId", query = "SELECT a FROM Airport a WHERE a.airportId = :airportId"),
    @NamedQuery(name = "Airport.findByCityId", query = "SELECT a FROM Airport a WHERE a.cityId = :cityId")})
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "shortname")
    private String shortname;
    @Basic(optional = false)
    @Column(name = "airport_id")
    private int airportId;
    @Column(name = "city_id")
    private Integer cityId;

    public Airport() {
    }

    public Airport(String shortname) {
        this.shortname = shortname;
    }

    public Airport(String shortname, int airportId) {
        this.shortname = shortname;
        this.airportId = airportId;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortname != null ? shortname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.shortname == null && other.shortname != null) || (this.shortname != null && !this.shortname.equals(other.shortname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Airport[ shortname=" + shortname + " ]";
    }
    
}
