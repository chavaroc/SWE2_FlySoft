package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.IAirport;
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
 * Class who represents the table airport in the database with all attributes.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "airport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a"),
    @NamedQuery(name = "Airport.findByShortName", query = "SELECT a FROM Airport a WHERE a.shortName = :shortName"),
    @NamedQuery(name = "Airport.findByAirportId", query = "SELECT a FROM Airport a WHERE a.airportId = :airportId"),
    @NamedQuery(name = "Airport.findByCityId", query = "SELECT a FROM Airport a WHERE a.cityId = :cityId")})
public class Airport implements Serializable, IAirport {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "shortname")
    private String shortName;
    @Basic(optional = false)
    @Column(name = "airport_id")
    private int airportId;
    @Column(name = "city_id")
    private Integer cityId;

    public Airport() {
    }

    public Airport(String shortName, int airportId) {
        this.shortName = shortName;
        this.airportId = airportId;
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
    public int getAirportId() {
        return airportId;
    }

    @Override
    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    @Override
    public Integer getCityId() {
        return cityId;
    }

    @Override
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortName != null ? shortName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // Note: The id is irrelevant for the compare (can diver from database and csv import)
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.shortName == null && other.shortName != null)
            || (this.shortName != null && !this.shortName.equals(other.shortName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Airport[ shortName=" + shortName + " ]";
    }
    
}
