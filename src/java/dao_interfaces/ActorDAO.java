/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_interfaces;

import entities.Actor;
import java.util.List;

/**
 *
 * @author Oksana_Moroz
 */
public interface ActorDAO {

    void create(Actor actor);
    
    List<Actor> findAll();
    
    Actor findById(Long id);
    
    void update(Actor actor);
    
    void delete(Actor actor) throws Exception;
}
