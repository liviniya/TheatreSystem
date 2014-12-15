/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.ContractDAO;
import entities.Contract;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Оксана
 */
@ManagedBean
@ViewScoped
public class BookkeeperSetSalaryBean {
    @EJB
    private ContractDAO jpaContractDAO;
    
    private Contract contract;
    private Integer salary;
    
    /**
     * Creates a new instance of BookkeeperSetSalaryBean
     */
    public BookkeeperSetSalaryBean() {
    }
    
    public List<Contract> getEmptySalaries() {
        return jpaContractDAO.findByEmptySalary();
    }
    
    public String saveContract() {
        contract.setSalary(salary);
        jpaContractDAO.update(contract);
        
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("salarySet"), null));
        return "bookkeeper_set_salary?faces-redirect=true";
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
}
