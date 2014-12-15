/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_interfaces;

import entities.RealPlayedRole;
import java.util.List;

/**
 *
 * @author Оксана
 */
public interface RealPlayedRoleDAO {

    void create(RealPlayedRole realPlayedRole);
    
    List<RealPlayedRole> findAll();
    
    RealPlayedRole findById(Long id);
    
    List<RealPlayedRole> findByEmptyPremium();
    
    void update(RealPlayedRole realPlayedRole);
    
    void delete(RealPlayedRole performance) throws Exception;
}
