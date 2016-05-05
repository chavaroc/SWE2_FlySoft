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
@Table(name = "datasourcetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datasourcetype.findAll", query = "SELECT d FROM Datasourcetype d"),
    @NamedQuery(name = "Datasourcetype.findByDatasourcetypeId", query = "SELECT d FROM Datasourcetype d WHERE d.datasourcetypeId = :datasourcetypeId"),
    @NamedQuery(name = "Datasourcetype.findByName", query = "SELECT d FROM Datasourcetype d WHERE d.name = :name")})
public class Datasourcetype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "datasourcetype_id")
    private Integer datasourcetypeId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Datasourcetype() {
    }

    public Datasourcetype(Integer datasourcetypeId) {
        this.datasourcetypeId = datasourcetypeId;
    }

    public Datasourcetype(Integer datasourcetypeId, String name) {
        this.datasourcetypeId = datasourcetypeId;
        this.name = name;
    }

    public Integer getDatasourcetypeId() {
        return datasourcetypeId;
    }

    public void setDatasourcetypeId(Integer datasourcetypeId) {
        this.datasourcetypeId = datasourcetypeId;
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
        hash += (datasourcetypeId != null ? datasourcetypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datasourcetype)) {
            return false;
        }
        Datasourcetype other = (Datasourcetype) object;
        if ((this.datasourcetypeId == null && other.datasourcetypeId != null) || (this.datasourcetypeId != null && !this.datasourcetypeId.equals(other.datasourcetypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Datasourcetype[ datasourcetypeId=" + datasourcetypeId + " ]";
    }
    
}
