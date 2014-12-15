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
import static entities.Position.Producer;
import entities.Role;
import entities.Worker;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

    private Role selectedRole;
    private Contract selectedContract;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private Performance performance;
    private Role role;
    private List<Contract> contractsByRole;
    private boolean renew = false;
    private List<Role> rolesByPerformance;

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
        if (rolesByPerformance == null || renew) {
            renew = false;
            rolesByPerformance = jpaRoleDAO.findByPerformance(performance);
        }
        return rolesByPerformance;
    }

    public void setRenew() {
        renew = true;
    }

    public List<Actor> getActors() {
        return jpaActorDAO.findAll();
    }

    public List<Role> getRoles() {
        return jpaRoleDAO.findAll();
    }

    public List<Contract> getContractsByRole() {
        if (contractsByRole == null || renew) {
            renew = false;
            contractsByRole = jpaContractDAO.findByRoleAll(role);
        }
        return contractsByRole;
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

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public Contract getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(Contract selectedContract) {
        this.selectedContract = selectedContract;
    }

    public void deleteSelectedRole() {
        try {
            renew = true;
            jpaRoleDAO.delete(selectedRole);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().getResourceBundle(context, "msg")
                    .getString("deleteImpossible"), null));

        }

    }
    
    public void deleteSelectedContract() {
        try {
            renew = true;
            jpaContractDAO.delete(selectedContract);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().getResourceBundle(context, "msg")
                    .getString("deleteImpossible"), null));

        }

    }
}
