/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb_beans;

import dao_interfaces.RealPlayedRoleDAO;
import entities.RealPlayedRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Оксана
 */
@Stateless
public class JpaRealPlayedRoleDAO extends JpaGenericDAO<RealPlayedRole> implements RealPlayedRoleDAO {

    public JpaRealPlayedRoleDAO() {
        super(RealPlayedRole.class);
    }
    
    @Override
    public List<RealPlayedRole> findByEmptyPremium() {
        TypedQuery<RealPlayedRole> q = em.createNamedQuery("RealPlayedRole.findByEmptyPremium",
                RealPlayedRole.class);
        return q.getResultList();
    }
}
