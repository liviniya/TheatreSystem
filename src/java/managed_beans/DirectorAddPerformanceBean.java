/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.PerformanceDAO;
import dao_interfaces.WorkerDAO;
import entities.Performance;
import entities.Position;
import entities.Worker;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@RequestScoped
public class DirectorAddPerformanceBean {
    @EJB
    private WorkerDAO jpaWorkerDAO;
    
    @EJB
    private PerformanceDAO jpaPerformanceDAO;
    
    private Performance performance = new Performance();
    
    /**
     * Creates a new instance of DirectorAddPerformanceBean
     */
    public DirectorAddPerformanceBean() {
    }
    
    public String savePerformance() {
        jpaPerformanceDAO.create(performance);
        return "director_add_performance?faces-redirect=true";
    }

    public List<Worker> getAllProducers() {
        return jpaWorkerDAO.findByPosition(Position.Producer);
    }
    
    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}
