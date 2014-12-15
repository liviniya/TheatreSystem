/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.ContractDAO;
import dao_interfaces.RoleDAO;
import entities.Contract;
import entities.Performance;
import entities.Role;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Оксана
 */
@ManagedBean
@ViewScoped
public class ProducerAddContractBean {

    @EJB
    private RoleDAO jpaRoleDAO;
    
    @EJB
    private ContractDAO jpaContractDAO;
    
    private Contract contract = new Contract();
    
    private Performance performance;
    
    @ManagedProperty(value = "#{producerViewBean}")
    private ProducerViewBean producerViewBean;
    
    /**
     * Creates a new instance of ProducerAddContractBean
     */
    public ProducerAddContractBean() {
    }
    
    public String saveContract() {
        jpaContractDAO.create(contract);
        contract = new Contract();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("newContractAdded"), null));
        return "producer_add_contract?faces-redirect=true";
    }
    
    public List<Role> getByPerformance() {
        if (performance == null) {
            List<Performance> my = producerViewBean.getMyPerformances();
            if (!my.isEmpty()) {
                performance = my.get(0);
            }
        }        
        return jpaRoleDAO.findByPerformance(performance);
    }
    
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
