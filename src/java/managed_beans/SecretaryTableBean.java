/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao_interfaces.ActorDAO;
import dao_interfaces.WorkerDAO;
import entities.Actor;
import entities.Worker;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@ViewScoped
public class SecretaryTableBean {

    @EJB
    private ActorDAO jpaActorDAO;

    @EJB
    private WorkerDAO jpaWorkerDAO;
    
    private List<Actor> actors;
    private Actor selectedActor;
    private boolean renew = false;
    /**
     * Creates a new instance of SecretaryBean
     */
    public SecretaryTableBean() {
    }

    public List<Worker> getWorkers() {
        return jpaWorkerDAO.findAll();
    }

    public List<Actor> getActors() {
        if (actors == null || renew) {
            renew = false;
            actors = jpaActorDAO.findAll();
        }
        return actors;
    }

    public void onActorEdit(RowEditEvent event) {
        Actor a = (Actor) event.getObject();
        jpaActorDAO.update(a);
    }

    public Actor getSelectedActor() {
        return selectedActor;
    }

    public void setSelectedActor(Actor actor) {
        this.selectedActor = actor;
    }
    
    public void deleteSelectedActor() {
        try {
            renew = true;
            jpaActorDAO.delete(selectedActor);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().getResourceBundle(context, "msg")
                    .getString("deleteImpossible"), null));
        }
    }
}
