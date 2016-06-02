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
 * Class who list the type of datasource.
 * @author Betina Hientz
 * @version 02.06.2016
 */
@Entity
@Table(name = "datasourcetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataSourceType.findAll", query = "SELECT d FROM DataSourceType d"),
    @NamedQuery(name = "DataSourceType.findByDataSourceTypeId", query = "SELECT d FROM DataSourceType d WHERE d.dataSourceTypeId = :dataSourceTypeId"),
    @NamedQuery(name = "DataSourceType.findByName", query = "SELECT d FROM DataSourceType d WHERE d.name = :name")})
public class DataSourceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "datasourcetype_id")
    private Integer dataSourceTypeId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public DataSourceType() {
    }

    public DataSourceType(Integer dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
    }

    public DataSourceType(Integer dataSourceTypeId, String name) {
        this.dataSourceTypeId = dataSourceTypeId;
        this.name = name;
    }

    public Integer getDataSourceTypeId() {
        return dataSourceTypeId;
    }

    public void setDataSourceTypeId(Integer dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
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
        hash += (dataSourceTypeId != null ? dataSourceTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataSourceType)) {
            return false;
        }
        DataSourceType other = (DataSourceType) object;
        if ((this.dataSourceTypeId == null && other.dataSourceTypeId != null) || (this.dataSourceTypeId != null && !this.dataSourceTypeId.equals(other.dataSourceTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hm.edu.swe2.flysoft.entity.DataSourceType[ dataSourceTypeId=" + dataSourceTypeId + " ]";
    }
    
}
