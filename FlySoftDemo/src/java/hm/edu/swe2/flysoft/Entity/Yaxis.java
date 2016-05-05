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
@Table(name = "yaxis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Yaxis.findAll", query = "SELECT y FROM Yaxis y"),
    @NamedQuery(name = "Yaxis.findByYaxisId", query = "SELECT y FROM Yaxis y WHERE y.yaxisId = :yaxisId"),
    @NamedQuery(name = "Yaxis.findByName", query = "SELECT y FROM Yaxis y WHERE y.name = :name")})
public class Yaxis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "yaxis_id")
    private Integer yaxisId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Yaxis() {
    }

    public Yaxis(Integer yaxisId) {
        this.yaxisId = yaxisId;
    }

    public Yaxis(Integer yaxisId, String name) {
        this.yaxisId = yaxisId;
        this.name = name;
    }

    public Integer getYaxisId() {
        return yaxisId;
    }

    public void setYaxisId(Integer yaxisId) {
        this.yaxisId = yaxisId;
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
        hash += (yaxisId != null ? yaxisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Yaxis)) {
            return false;
        }
        Yaxis other = (Yaxis) object;
        if ((this.yaxisId == null && other.yaxisId != null) || (this.yaxisId != null && !this.yaxisId.equals(other.yaxisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.Yaxis[ yaxisId=" + yaxisId + " ]";
    }
    
}
