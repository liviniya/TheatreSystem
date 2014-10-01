/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.ContractDAO;
import dao_interfaces.RealPlayedRoleDAO;
import dao_interfaces.RoleDAO;
import entities.Actor;
import entities.Contract;
import entities.Performance;
import entities.RealPlayedRole;
import entities.Role;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Оксана
 */
@ManagedBean
@ViewScoped
public class ProducerAddRealRoleBean {
    @EJB
    private RealPlayedRoleDAO jpaRealPlayedRoleDAO;
    
    @EJB
    private ContractDAO jpaContractDAO;
    
    @EJB
    private RoleDAO jpaRoleDAO;  
            
    private RealPlayedRole realPlayedRole = new RealPlayedRole();
    
    private Performance performance;
    
    @ManagedProperty(value = "#{producerViewBean}")
    private ProducerViewBean producerViewBean;
    
    /**
     * Creates a new instance of ProducerAddRealRoleBean
     */
    public ProducerAddRealRoleBean() {
    }
    
    public List<Actor> getActorsByRole() {
        return jpaContractDAO.findByRole(realPlayedRole.getRealPlayedRole());
    }
    
    public List<Role> getRolesByPerformance() {        
        if (performance == null) {
            List<Performance> my = producerViewBean.getMyPerformances();            
            if (!my.isEmpty()) {
                performance = my.get(0);
            }
        }      
        return jpaRoleDAO.findByPerformance(performance);
    }
    
    public String saveRealPlayedRole() {        
        List<Contract> contractToShut = jpaContractDAO.findByRoleEndDate(
                realPlayedRole.getRealPlayedRole());
        for (Contract c : contractToShut) {
            c.setEndDate(new Date());
            jpaContractDAO.update(c);
        }
        jpaRealPlayedRoleDAO.create(realPlayedRole);
        return "producer_add_real_role?faces-redirect=true";
    }

    public RealPlayedRole getRealPlayedRole() {
        return realPlayedRole;
    }

    public void setRealPlayedRole(RealPlayedRole realPlayedRole) {
        this.realPlayedRole = realPlayedRole;
    }   

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }    

    public ProducerViewBean getProducerViewBean() {
        return producerViewBean;
    }

    public void setProducerViewBean(ProducerViewBean producerViewBean) {
        this.producerViewBean = producerViewBean;
    }
}