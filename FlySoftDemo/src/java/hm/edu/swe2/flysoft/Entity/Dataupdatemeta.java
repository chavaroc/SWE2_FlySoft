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
 * Class to update meta data.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "dataupdatemeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataUpdateMeta.findAll", query = "SELECT d FROM DataUpdateMeta d"),
    @NamedQuery(name = "DataUpdateMeta.findByDataUpdateMetaId", query = "SELECT d FROM DataUpdateMeta d WHERE d.dataUpdateMetaId = :dataUpdateMetaId"),
    @NamedQuery(name = "DataUpdateMeta.findByHash", query = "SELECT d FROM DataUpdateMeta d WHERE d.hash = :hash"),
    @NamedQuery(name = "DataUpdateMeta.findByDataSourceTypeId", query = "SELECT d FROM DataUpdateMeta d WHERE d.dataSourceTypeId = :dataSourceTypeId")})
public class DataUpdateMeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dataupdatemeta_id")
    private Integer dataUpdateMetaId;
    @Basic(optional = false)
    @Column(name = "hash")
    private String hash;
    @Column(name = "datasourcetype_id")
    private Integer dataSourceTypeId;

    public DataUpdateMeta() {
    }

    public DataUpdateMeta(Integer dataUpdateMetaId) {
        this.dataUpdateMetaId = dataUpdateMetaId;
    }

    public DataUpdateMeta(Integer dataUpdateMetaId, String hash) {
        this.dataUpdateMetaId = dataUpdateMetaId;
        this.hash = hash;
    }

    public Integer getDataUpdateMetaId() {
        return dataUpdateMetaId;
    }

    public void setDataUpdateMetaId(Integer dataUpdateMetaId) {
        this.dataUpdateMetaId = dataUpdateMetaId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getDataSourceTypeId() {
        return dataSourceTypeId;
    }

    public void setDataSourceTypeId(Integer dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataUpdateMetaId != null ? dataUpdateMetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataUpdateMeta)) {
            return false;
        }
        DataUpdateMeta other = (DataUpdateMeta) object;
        if ((this.dataUpdateMetaId == null && other.dataUpdateMetaId != null) || (this.dataUpdateMetaId != null && !this.dataUpdateMetaId.equals(other.dataUpdateMetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.DataUpdateMeta[ dataUpdateMetaId=" + dataUpdateMetaId + " ]";
    }
    
}
