/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.WorkerDAO;
import entities.Position;
import entities.User;
import entities.Worker;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import utils.PasswordEncryptor;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@ViewScoped
public class DirectorAddWorkerBean {
    
    @EJB
    private WorkerDAO jpaWorkerDAO;

    private Worker worker = new Worker();
    
    /**
     * Creates a new instance of DirectorAddWorkerBean
     */
    public DirectorAddWorkerBean() {
        worker.setUser(new User());
    }

    public List<Position> getAllPositions() {
        return jpaWorkerDAO.findAllPositions();
    }
    
    public String saveWorker() {
        worker.getUser().setPassword((new PasswordEncryptor()).getEncoded(worker.getUser().getPassword()));
        jpaWorkerDAO.create(worker);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("newWorkerAdded"), null));
        return "director_add_worker?faces-redirect=true";
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    } 
}
