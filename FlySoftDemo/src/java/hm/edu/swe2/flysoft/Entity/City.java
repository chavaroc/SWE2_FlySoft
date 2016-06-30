package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.interfaces.ICity;
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
 * Class who represents the table city in the database with all attributes.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByCityId", query = "SELECT c FROM City c WHERE c.cityId = :cityId"),
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name")})
public class City implements Serializable, ICity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "city_id")
    private Integer cityId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public City() {
    }

    public City(Integer cityId, String name) {
        this.cityId = cityId;
        this.name = name;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.cityId == null && other.cityId != null)
            || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.City[ cityId=" + cityId + " ]";
    }
    
}
