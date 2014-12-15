/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_interfaces;

import entities.Performance;
import entities.Role;
import java.util.List;

/**
 *
 * @author Oksana_Moroz
 */
public interface RoleDAO {

    void create(Role role);
    
    List<Role> findByPerformance(Performance performance);
    
    Role findById(Long id);
    
    List<Role> findAll();
    
    void delete(Role role) throws Exception;
}
