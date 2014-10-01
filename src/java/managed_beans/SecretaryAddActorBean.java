/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed_beans;

import dao_interfaces.ActorDAO;
import entities.Actor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
        return "secretary_add_actor?faces-redirect=true";
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }    
}
