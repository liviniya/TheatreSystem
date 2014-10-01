/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "performances")
@NamedQueries({
    @NamedQuery(name = "Performance.findAll", query = "select p from Performance p"),
    @NamedQuery(name = "Performance.findByProducer", query = "select p from Performance p where p.producer = :producer"),
    @NamedQuery(name = "Performance.findByEmptyBudget", query = "select p from Performance p where p.budget is null")
})
public class Performance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "performance_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "performance_date")
    private Date performanceDate;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "budget")
    private Integer budget;

    @OneToMany(mappedBy = "performance")
    private List<Role> roles = new ArrayList<Role>();
    
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker producer;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return performanceDate;
    }

    public void setDate(Date date) {
        this.performanceDate = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }   

    public Worker getProducer() {
        return producer;
    }

    public void setProducer(Worker producer) {
        this.producer = producer;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }   
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 97 * hash + (this.performanceDate != null ? this.performanceDate.hashCode() : 0);
        hash = 97 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 97 * hash + (this.budget != null ? this.budget.hashCode() : 0);
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
        final Performance other = (Performance) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if (this.performanceDate != other.performanceDate && (this.performanceDate == null || !this.performanceDate.equals(other.performanceDate))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.budget != other.budget && (this.budget == null || !this.budget.equals(other.budget))) {
            return false;
        }
        return true;
    }   
    
    @Override
    public String toString() {
        return "Performance[ id=" + id + ", title=" + title + ", date=" + performanceDate 
                + ", description=" + description + ", budget=" + budget + " ]";
    }
    
}
