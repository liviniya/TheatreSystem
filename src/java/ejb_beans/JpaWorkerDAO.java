/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb_beans;

import dao_interfaces.WorkerDAO;
import entities.Position;
import entities.User;
import entities.Worker;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oksana_Moroz
 */
@Stateless
public class JpaWorkerDAO extends JpaGenericDAO<Worker> implements WorkerDAO {

    public JpaWorkerDAO() {
        super(Worker.class);
    }  
    
    @Override
    public Worker findByLoginPassword(String login, String password) {         
        TypedQuery<Worker> q = em.createNamedQuery("Worker.findByLoginPassword", Worker.class);
        q.setParameter("login", login).setParameter("password", password);        
        List<Worker> ws = q.getResultList();              
        if (ws.isEmpty()) {            
            return null;
        } else {            
            return ws.get(0);
        }
    }
    
    @Override
    public List<Worker> findByPosition(Position position) {
        TypedQuery<Worker> q = em.createNamedQuery("Worker.findByPosition",
                Worker.class);
        q.setParameter("position", position);
        return q.getResultList();
    }

    @Override
    public List<Position> findAllPositions() {
        return Arrays.asList(Position.values());
    } 
    
    @Override
    public void create(Worker worker) {
        User user = worker.getUser();
        worker.setUser(null);
        em.persist(worker);
        user.setWorker(worker);
        em.persist(user);
    }
    
    
}