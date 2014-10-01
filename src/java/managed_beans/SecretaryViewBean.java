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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean
@RequestScoped
public class SecretaryViewBean {

    @EJB
    private ActorDAO jpaActorDAO;

    @EJB
    private WorkerDAO jpaWorkerDAO;

    /**
     * Creates a new instance of SecretaryBean
     */
    public SecretaryViewBean() {
    }

    public List<Worker> getWorkers() {
        return jpaWorkerDAO.findAll();
    }

    public List<Actor> getActors() {
        return jpaActorDAO.findAll();
    }

}
