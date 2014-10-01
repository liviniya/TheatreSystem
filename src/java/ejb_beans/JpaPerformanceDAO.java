/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb_beans;

import dao_interfaces.PerformanceDAO;
import entities.Performance;
import entities.Worker;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oksana_Moroz
 */
@Stateless
public class JpaPerformanceDAO extends JpaGenericDAO<Performance> implements PerformanceDAO {

    public JpaPerformanceDAO() {
        super(Performance.class);
    }

    @Override
    public List<Performance> findByProducer(Worker producer) {
        TypedQuery<Performance> q = em.createNamedQuery("Performance.findByProducer",
                Performance.class);
        q.setParameter("producer", producer);
        return q.getResultList();
    }

    @Override
    public List<Performance> findByEmptyBudget() {
        TypedQuery<Performance> q = em.createNamedQuery("Performance.findByEmptyBudget",
                Performance.class);
        return q.getResultList();
    }
}
