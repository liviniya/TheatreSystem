/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.PerformanceDAO;
import entities.Performance;
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
public class BookkeeperSetBudgetBean {
    @EJB
    private PerformanceDAO jpaPerformanceDAO;

    private Performance performance;
    
    private Integer budget;
    
    /**
     * Creates a new instance of BookkeeperSetBudgetBean
     */
    public BookkeeperSetBudgetBean() {
    }
    
    public List<Performance> getEmptyBudgets() {        
        return jpaPerformanceDAO.findByEmptyBudget();
    }
    
    public String savePerformance() {
        performance.setBudget(budget);
        jpaPerformanceDAO.update(performance);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("budgetSet"), null));
        return "bookkeeper_set_budget?faces-redirect=true";
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }  

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}
