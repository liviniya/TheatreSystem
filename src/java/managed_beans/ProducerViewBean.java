/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.ActorDAO;
import dao_interfaces.ContractDAO;
import dao_interfaces.PerformanceDAO;
import dao_interfaces.RoleDAO;
import entities.Actor;
import entities.Contract;
import entities.Performance;
import entities.Role;
import entities.Worker;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@ViewScoped
public class ProducerViewBean {   
    @EJB
    private ContractDAO jpaContractDAO;
    
    @EJB
    private ActorDAO jpaActorDAO;
    
    @EJB
    private RoleDAO jpaRoleDAO;
    
    @EJB
    private PerformanceDAO jpaPerformanceDAO;    
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;   
    
    private Performance performance;
    private Role role;
    
    /**
     * Creates a new instance of ProducerBean
     */
    public ProducerViewBean() {
    }
    
    public List<Performance> getMyPerformances() {
        Worker me = loginBean.getWorker();       
        return jpaPerformanceDAO.findByProducer(me);        
    }
    
    public List<Role> getRolesByPerformance() {
        return jpaRoleDAO.findByPerformance(performance);
    }
    
    public List<Actor> getActors() {
        return jpaActorDAO.findAll();
    }
    
    public List<Role> getRoles() {
        return jpaRoleDAO.findAll();
    } 
    
    public List<Contract> getContractsByRole() {
        return jpaContractDAO.findByRoleAll(role);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }      

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
