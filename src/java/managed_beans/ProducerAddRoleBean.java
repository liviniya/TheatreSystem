/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.RoleDAO;
import entities.Performance;
import entities.Role;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Оксана
 */
@ManagedBean
@RequestScoped
public class ProducerAddRoleBean {

    @EJB
    private RoleDAO jpaRoleDAO;
    
    private Role role = new Role();
    
    /**
     * Creates a new instance of ProducerAddNewRoleBean
     */
    public ProducerAddRoleBean() {
    }
    
    public String saveRole() {
        jpaRoleDAO.create(role);        
        role = new Role(); 
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("newRoleAdded"), null));
        return "producer_add_role?faces-redirect=true";
    }
    
    public void setRolePerformance(Performance performance) {
        this.role.setPerformance(performance);
    } 

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }    
}
