/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb_beans;

import dao_interfaces.ContractDAO;
import entities.Actor;
import entities.Contract;
import entities.Role;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oksana_Moroz
 */
@Stateless
public class JpaContractDAO extends JpaGenericDAO<Contract> implements ContractDAO {

    public JpaContractDAO() {
        super(Contract.class);
    }

    @Override
    public List<Actor> findByRole(Role role) {
        Date current = new Date();
        TypedQuery<Actor> q = em.createNamedQuery("Contract.findByRole", Actor.class);
        q.setParameter("role", role);
        q.setParameter("current", current);
        return q.getResultList();
    }

    @Override
    public List<Contract> findByRoleEndDate(Role role) {
        Date current = new Date();
        TypedQuery<Contract> q = em.createNamedQuery("Contract.findByRoleAndDate",
                Contract.class);
        q.setParameter("role", role);
        q.setParameter("current", current);
        return q.getResultList();
    }

    @Override
    public List<Contract> findByEmptySalary() {
        TypedQuery<Contract> q = em.createNamedQuery("Contract.findByEmptySalary",
                Contract.class);
        return q.getResultList();
    }

    @Override
    public List<Contract> findByRoleAll(Role role) {
        TypedQuery<Contract> q = em.createNamedQuery("Contract.findByRoleAll",
                Contract.class);
        q.setParameter("role", role);        
        return q.getResultList();
    }
}
