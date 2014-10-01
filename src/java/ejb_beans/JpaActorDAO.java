/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb_beans;

import dao_interfaces.ActorDAO;
import entities.Actor;
import javax.ejb.Stateless;

/**
 *
 * @author Oksana_Moroz
 */
@Stateless
public class JpaActorDAO extends JpaGenericDAO<Actor> implements ActorDAO {

    public JpaActorDAO() {
        super(Actor.class);
    } 
}
