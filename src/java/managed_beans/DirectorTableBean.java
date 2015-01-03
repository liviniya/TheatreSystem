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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@ViewScoped
public class DirectorTableBean {

    @EJB
    private WorkerDAO jpaWorkerDAO;

    @EJB
    private PerformanceDAO jpaPerformanceDAO;

    private List<Performance> performances;
    private List<Worker> workers;

    private Performance selectedPerformance;
    private Worker selectedWorker;

    private boolean renew = false;

    /**
     * Creates a new instance of DirectorViewBean
     */
    public DirectorTableBean() {
    }

    public List<Performance> getAllPerformances() {
        if (performances == null || renew) {
            renew = false;
            performances = jpaPerformanceDAO.findAll();
        }
        return performances;
    }

    public List<Worker> getAllWorkers() {
        if (workers == null || renew) {
            renew = false;
            workers = jpaWorkerDAO.findAll();
        }
        return workers;
    }

    public void onPerformanceEdit(RowEditEvent event) {
        Performance p = (Performance) event.getObject();
        jpaPerformanceDAO.update(p);
    }

    public void onWorkerEdit(RowEditEvent event) {
        Worker w = (Worker) event.getObject();
        jpaWorkerDAO.update(w);
    }

    public List<Worker> getAllProducers() {
        return jpaWorkerDAO.findByPosition(Position.Producer);
    }

    public List<Position> getAllPositions() {
        return jpaWorkerDAO.findAllPositions();
    }

    public Performance getSelectedPerformance() {
        return selectedPerformance;
    }

    public void setSelectedPerformance(Performance selectedPerformance) {
        this.selectedPerformance = selectedPerformance;
    }

    public Worker getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(Worker selectedWorker) {
        this.selectedWorker = selectedWorker;
    }

    public void deleteSelectedPerformance() {
        try {
            renew = true;
            jpaPerformanceDAO.delete(selectedPerformance);            
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().getResourceBundle(context, "msg")
                    .getString("deleteImpossible"), null));
            
        }
        
    }

    
}
