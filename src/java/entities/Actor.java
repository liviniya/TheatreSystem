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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "actors")
@NamedQuery(name = "Actor.findAll", query = "select a from Actor a")
public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "rewards")
    private String rewards;
    
    @Column(name = "experience")
    private Integer experience;
    
    @OneToMany(mappedBy = "actor")
    private List<Contract> contracts = new ArrayList<Contract>();
    
    @OneToMany(mappedBy = "realPlayedActor")
    private List<RealPlayedRole> realPlayedRoles = new ArrayList<RealPlayedRole>();
    
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<RealPlayedRole> getRealPlayedRoles() {
        return realPlayedRoles;
    }

    public void setRealPlayedRoles(List<RealPlayedRole> realPlayedRoles) {
        this.realPlayedRoles = realPlayedRoles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.rewards != null ? this.rewards.hashCode() : 0);
        hash = 29 * hash + (this.experience != null ? this.experience.hashCode() : 0);
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
        final Actor other = (Actor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.rewards == null) ? (other.rewards != null) : !this.rewards.equals(other.rewards)) {
            return false;
        }
        if (this.experience != other.experience && (this.experience == null || !this.experience.equals(other.experience))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Actor[ id=" + id + ", surname=" + surname + ", name=" + name 
                + ", rewards=" + rewards + ", experience=" + experience + " ]";
    }
    
}
