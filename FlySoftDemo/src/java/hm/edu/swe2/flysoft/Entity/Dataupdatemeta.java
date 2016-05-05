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
@Table(name = "dataupdatemeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dataupdatemeta.findAll", query = "SELECT d FROM Dataupdatemeta d"),
    @NamedQuery(name = "Dataupdatemeta.findByDataupdatemetaId", query = "SELECT d FROM Dataupdatemeta d WHERE d.dataupdatemetaId = :dataupdatemetaId"),
    @NamedQuery(name = "Dataupdatemeta.findByHash", query = "SELECT d FROM Dataupdatemeta d WHERE d.hash = :hash"),
    @NamedQuery(name = "Dataupdatemeta.findByDatasourcetypeId", query = "SELECT d FROM Dataupdatemeta d WHERE d.datasourcetypeId = :datasourcetypeId")})
public class Dataupdatemeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dataupdatemeta_id")
    private Integer dataupdatemetaId;
    @Basic(optional = false)
    @Column(name = "hash")
    private String hash;
    @Column(name = "datasourcetype_id")
    private Integer datasourcetypeId;

    public Dataupdatemeta() {
    }

    public Dataupdatemeta(Integer dataupdatemetaId) {
        this.dataupdatemetaId = dataupdatemetaId;
    }

    public Dataupdatemeta(Integer dataupdatemetaId, String hash) {
        this.dataupdatemetaId = dataupdatemetaId;
        this.hash = hash;
    }

    public Integer getDataupdatemetaId() {
        return dataupdatemetaId;
    }

    public void setDataupdatemetaId(Integer dataupdatemetaId) {
        this.dataupdatemetaId = dataupdatemetaId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getDatasourcetypeId() {
        return datasourcetypeId;
    }

    public void setDatasourcetypeId(Integer datasourcetypeId) {
        this.datasourcetypeId = datasourcetypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataupdatemetaId != null ? dataupdatemetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dataupdatemeta)) {
            return false;
        }
        Dataupdatemeta other = (Dataupdatemeta) object;
        if ((this.dataupdatemetaId == null && other.dataupdatemetaId != null) || (this.dataupdatemetaId != null && !this.dataupdatemetaId.equals(other.dataupdatemetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Dataupdatemeta[ dataupdatemetaId=" + dataupdatemetaId + " ]";
    }
    
}
