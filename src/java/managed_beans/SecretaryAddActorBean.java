/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import dao_interfaces.ActorDAO;
import entities.Actor;
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
public class SecretaryAddActorBean {

    @EJB
    private ActorDAO jpaActorDAO;

    private Actor actor = new Actor();

    /**
     * Creates a new instance of SecretaryAddActorBean
     */
    public SecretaryAddActorBean() {
    }

    public String saveActor() {
        jpaActorDAO.create(actor);
        actor = new Actor();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        context.getApplication().getResourceBundle(context, "msg")
                            .getString("newActorAdded"), null));
        return "secretary_add_actor?faces-redirect=true";
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
