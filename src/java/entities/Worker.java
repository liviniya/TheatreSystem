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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "workers")
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "select w from Worker w"),
    @NamedQuery(name = "Worker.findByLoginPassword", query = "select w from Worker w"
            + " where w.user.login = :login and w.user.password = :password"),
    @NamedQuery(name = "Worker.findByPosition", query = "select w from Worker w where w.position = :position")
})
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "surname", nullable = false)
    private String surname;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "start_work_date", nullable = false)
    private Date startWorkDate;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "end_work_date")
    private Date endWorkDate;
    
    @OneToOne(mappedBy = "worker")
    private User user;
    
    @OneToMany(mappedBy = "producer")
    private List<Performance> performances = new ArrayList<Performance>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public Date getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(Date endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 19 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 19 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 19 * hash + (this.startWorkDate != null ? this.startWorkDate.hashCode() : 0);
        hash = 19 * hash + (this.endWorkDate != null ? this.endWorkDate.hashCode() : 0);
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
        final Worker other = (Worker) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        if (this.startWorkDate != other.startWorkDate && (this.startWorkDate == null || !this.startWorkDate.equals(other.startWorkDate))) {
            return false;
        }
        if (this.endWorkDate != other.endWorkDate && (this.endWorkDate == null || !this.endWorkDate.equals(other.endWorkDate))) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "Worker[ id=" + id + ", surname=" + surname + ", name=" + name 
                + ", position=" + position.toString() + ", start_work_date="
                + startWorkDate + ", end_work_date=" + endWorkDate + " ]";
    }
    
}
