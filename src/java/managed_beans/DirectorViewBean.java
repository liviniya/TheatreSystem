/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.PerformanceDAO;
import dao_interfaces.WorkerDAO;
import entities.Performance;
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
public class DirectorViewBean {
    @EJB
    private WorkerDAO jpaWorkerDAO;
    
    @EJB
    private PerformanceDAO jpaPerformanceDAO;

    /**
     * Creates a new instance of DirectorViewBean
     */
    public DirectorViewBean() {
    }
    
    public List<Performance> getAllPerformances() {
        return jpaPerformanceDAO.findAll();
    }
    
    public List<Worker> getAllWorkers() {
        return jpaWorkerDAO.findAll();
    }
}
