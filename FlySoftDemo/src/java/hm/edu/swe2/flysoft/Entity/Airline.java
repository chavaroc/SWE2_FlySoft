package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IAirline;
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
 * Class who represents the table airline in the database with all attributes.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "airline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airline.findAll", query = "SELECT a FROM Airline a"),
    @NamedQuery(name = "Airline.findByAirlineId", query = "SELECT a FROM Airline a WHERE a.airlineId = :airlineId"),
    @NamedQuery(name = "Airline.findByName", query = "SELECT a FROM Airline a WHERE a.name = :name"),
    @NamedQuery(name = "Airline.findByShortName", query = "SELECT a FROM Airline a WHERE a.shortName = :shortName")})
public class Airline implements Serializable, IAirline {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "airline_id")
    private Integer airlineId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "shortname")
    private String shortName;

    public Airline() {
    }

    public Airline(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public Airline(Integer airlineId, String name, String shortName) {
        this.airlineId = airlineId;
        this.name = name;
        this.shortName = shortName;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    @Override
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airlineId != null ? airlineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airline)) {
            return false;
        }
        Airline other = (Airline) object;
        if ((this.airlineId == null && other.airlineId != null) || (this.airlineId != null && !this.airlineId.equals(other.airlineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Airline[ airlineId=" + airlineId + " ]";
    }
    
}
