/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb_beans;

import dao_interfaces.RoleDAO;
import entities.Performance;
import entities.Role;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oksana_Moroz
 */
@Stateless
public class JpaRoleDAO extends JpaGenericDAO<Role> implements RoleDAO {

    public JpaRoleDAO() {
        super(Role.class);
    }
    
    @Override
    public List<Role> findByPerformance(Performance performance) {
        TypedQuery<Role> q = em.createNamedQuery("Role.findByPerformance",
                Role.class);
        q.setParameter("performance", performance);
        return q.getResultList();
    }
}
