/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.RealPlayedRoleDAO;
import entities.RealPlayedRole;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Оксана
 */
@ManagedBean
@ViewScoped
public class BookkeeperSetPremiumBean {
    @EJB
    private RealPlayedRoleDAO jpaRealPlayedRoleDAO;

    private RealPlayedRole realRole;
    private Integer premium;
    
    /**
     * Creates a new instance of BookkeeperSetPremiumBean
     */
    public BookkeeperSetPremiumBean() {
    }
    
    public List<RealPlayedRole> getEmptyPremiums() {
        return jpaRealPlayedRoleDAO.findByEmptyPremium();
    }
    
    public String saveRealRole() {
        realRole.setPremium(premium);
        realRole.setDate(new Date());
        jpaRealPlayedRoleDAO.update(realRole);
        return "bookkeeper_set_premium?faces-redirect=true";
    }

    public RealPlayedRole getRealRole() {
        return realRole;
    }

    public void setRealRole(RealPlayedRole realRole) {
        this.realRole = realRole;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }
    
}
