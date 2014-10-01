/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "contracts")
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "select c from Contract c"),
    @NamedQuery(name = "Contract.findByRole", query = "select c.actor from Contract c where "
                + "c.role = :role and (c.endDate is null or c.endDate > :current)"), 
    @NamedQuery(name = "Contract.findByRoleAndDate", query = "select c from Contract c where "
                + "c.role = :role and (c.endDate is null or c.endDate > :current)"),
    @NamedQuery(name = "Contract.findByEmptySalary", query = "select c from Contract c where c.salary is null"),
    @NamedQuery(name = "Contract.findByRoleAll", query = "select c from Contract c where c.role = :role")
})
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "salary")
    private Integer salary;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 13 * hash + (this.salary != null ? this.salary.hashCode() : 0);
        hash = 13 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        hash = 13 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
        hash = 13 * hash + (this.actor != null ? this.actor.hashCode() : 0);
        hash = 13 * hash + (this.role != null ? this.role.hashCode() : 0);
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
        final Contract other = (Contract) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.salary != other.salary && (this.salary == null || !this.salary.equals(other.salary))) {
            return false;
        }
        if (this.startDate != other.startDate && (this.startDate == null || !this.startDate.equals(other.startDate))) {
            return false;
        }
        if (this.endDate != other.endDate && (this.endDate == null || !this.endDate.equals(other.endDate))) {
            return false;
        }
        if (this.actor != other.actor && (this.actor == null || !this.actor.equals(other.actor))) {
            return false;
        }
        if (this.role != other.role && (this.role == null || !this.role.equals(other.role))) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "Contract[ id=" + id + ", actor=" + actor.getName() + ", role="
                + role.getName() + ", salary=" + salary + ", start_date=" 
                + startDate + ", end_date=" + endDate + " ]";
    }
    
}
