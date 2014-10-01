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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oksana_Moroz
 */
@Entity @Table(name = "real_played_roles")
@NamedQueries({
    @NamedQuery(name = "RealPlayedRole.findAll", query = "select rr from RealPlayedRole rr"),
    @NamedQuery(name = "RealPlayedRole.findByEmptyPremium", query = "select rr from RealPlayedRole rr where rr.premium is null")
})
public class RealPlayedRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "real_played_role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "premium")
    private Integer premium;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "mark_date")
    private Date markDate;
    
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor realPlayedActor;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role realPlayedRole;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public Date getDate() {
        return markDate;
    }

    public void setDate(Date date) {
        this.markDate = date;
    }

    public Actor getRealPlayedActor() {
        return realPlayedActor;
    }

    public void setRealPlayedActor(Actor realPlayedActor) {
        this.realPlayedActor = realPlayedActor;
    }

    public Role getRealPlayedRole() {
        return realPlayedRole;
    }

    public void setRealPlayedRole(Role realPlayedRole) {
        this.realPlayedRole = realPlayedRole;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.premium != null ? this.premium.hashCode() : 0);
        hash = 17 * hash + (this.markDate != null ? this.markDate.hashCode() : 0);
        hash = 17 * hash + (this.realPlayedActor != null ? this.realPlayedActor.hashCode() : 0);
        hash = 17 * hash + (this.realPlayedRole != null ? this.realPlayedRole.hashCode() : 0);
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
        final RealPlayedRole other = (RealPlayedRole) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.premium != other.premium && (this.premium == null || !this.premium.equals(other.premium))) {
            return false;
        }
        if (this.markDate != other.markDate && (this.markDate == null || !this.markDate.equals(other.markDate))) {
            return false;
        }
        if (this.realPlayedActor != other.realPlayedActor && (this.realPlayedActor == null || !this.realPlayedActor.equals(other.realPlayedActor))) {
            return false;
        }
        if (this.realPlayedRole != other.realPlayedRole && (this.realPlayedRole == null || !this.realPlayedRole.equals(other.realPlayedRole))) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "RealPlayedRole[ id=" + id + ", actor=" + realPlayedActor.getName()
                + ", role=" + realPlayedRole.getName() + ", premium=" + premium 
                + ", date=" + markDate + " ]";
    }
    
}
