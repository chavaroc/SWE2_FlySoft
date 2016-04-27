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
@Table(name = "xaxis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Xaxis.findAll", query = "SELECT x FROM Xaxis x"),
    @NamedQuery(name = "Xaxis.findByXaxisId", query = "SELECT x FROM Xaxis x WHERE x.xaxisId = :xaxisId"),
    @NamedQuery(name = "Xaxis.findByName", query = "SELECT x FROM Xaxis x WHERE x.name = :name")})
public class Xaxis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "xaxis_id")
    private Integer xaxisId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Xaxis() {
    }

    public Xaxis(Integer xaxisId) {
        this.xaxisId = xaxisId;
    }

    public Xaxis(Integer xaxisId, String name) {
        this.xaxisId = xaxisId;
        this.name = name;
    }

    public Integer getXaxisId() {
        return xaxisId;
    }

    public void setXaxisId(Integer xaxisId) {
        this.xaxisId = xaxisId;
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
        hash += (xaxisId != null ? xaxisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Xaxis)) {
            return false;
        }
        Xaxis other = (Xaxis) object;
        if ((this.xaxisId == null && other.xaxisId != null) || (this.xaxisId != null && !this.xaxisId.equals(other.xaxisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.Entities.Xaxis[ xaxisId=" + xaxisId + " ]";
    }
    
}
