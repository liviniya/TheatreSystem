/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "select r from Role r"),
    @NamedQuery(name = "Role.findByPerformance", query = "select r from Role r where r.performance = :performance")
})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;
    
    @OneToMany(mappedBy = "role")
    private List<Contract> contracts = new ArrayList<Contract>();
    
    @OneToOne(mappedBy = "realPlayedRole")    
    private RealPlayedRole role;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }    

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public RealPlayedRole getRole() {
        return role;
    }

    public void setRole(RealPlayedRole role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.performance != null ? this.performance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.performance != other.performance && (this.performance == null || !this.performance.equals(other.performance))) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return "Role[ id=" + id + ", name=" + name + ", performance=" 
                + performance.getTitle() + " ]";
    }    
}