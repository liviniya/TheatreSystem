/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao_interfaces;

import entities.Performance;
import entities.Worker;
import java.util.List;

/**
 *
 * @author Oksana_Moroz
 */
public interface PerformanceDAO {
    
    List<Performance> findByProducer(Worker producer);
    
    Performance findById(Long id);
    
    List<Performance> findAll();
    
    List<Performance> findByEmptyBudget();
    
    void update(Performance performance);
    
    void create(Performance performance);
    
    void delete(Performance performance) throws Exception;
}
