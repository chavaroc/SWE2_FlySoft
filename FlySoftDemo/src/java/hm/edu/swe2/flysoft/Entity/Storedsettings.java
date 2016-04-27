/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.Entity;

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
@Table(name = "storedsettings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storedsettings.findAll", query = "SELECT s FROM Storedsettings s"),
    @NamedQuery(name = "Storedsettings.findByStoredsettingsId", query = "SELECT s FROM Storedsettings s WHERE s.storedsettingsId = :storedsettingsId"),
    @NamedQuery(name = "Storedsettings.findByName", query = "SELECT s FROM Storedsettings s WHERE s.name = :name"),
    @NamedQuery(name = "Storedsettings.findByStoredby", query = "SELECT s FROM Storedsettings s WHERE s.storedby = :storedby"),
    @NamedQuery(name = "Storedsettings.findByCreationdate", query = "SELECT s FROM Storedsettings s WHERE s.creationdate = :creationdate")})
public class Storedsettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "storedsettings_id")
    private Integer storedsettingsId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "storedby")
    private String storedby;
    @Basic(optional = false)
    @Column(name = "creationdate")
    @Temporal(TemporalType.DATE)
    private Date creationdate;

    public Storedsettings() {
    }

    public Storedsettings(Integer storedsettingsId) {
        this.storedsettingsId = storedsettingsId;
    }

    public Storedsettings(Integer storedsettingsId, String name, String storedby, Date creationdate) {
        this.storedsettingsId = storedsettingsId;
        this.name = name;
        this.storedby = storedby;
        this.creationdate = creationdate;
    }

    public Integer getStoredsettingsId() {
        return storedsettingsId;
    }

    public void setStoredsettingsId(Integer storedsettingsId) {
        this.storedsettingsId = storedsettingsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoredby() {
        return storedby;
    }

    public void setStoredby(String storedby) {
        this.storedby = storedby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storedsettingsId != null ? storedsettingsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storedsettings)) {
            return false;
        }
        Storedsettings other = (Storedsettings) object;
        if ((this.storedsettingsId == null && other.storedsettingsId != null) || (this.storedsettingsId != null && !this.storedsettingsId.equals(other.storedsettingsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.Entities.Storedsettings[ storedsettingsId=" + storedsettingsId + " ]";
    }
    
}
