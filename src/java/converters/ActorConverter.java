/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.ActorDAO;
import entities.Actor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Oksana_Moroz
 */
@ManagedBean(name = "actorConverterBean")
@FacesConverter("converters.actorConverterBean")
public class ActorConverter implements Converter {
    
    ActorDAO jpaActorDAO = lookupJpaActorDAOLocal();    
    
    /**
     * Creates a new instance of ActorConverter
     */
    public ActorConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaActorDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Actor actor = (Actor) value;
        return actor.getId().toString();
    }

    private ActorDAO lookupJpaActorDAOLocal() {
        try {
            Context c = new InitialContext();
            return (ActorDAO) c.lookup("java:global/TheatreSystem/JpaActorDAO!dao_interfaces.ActorDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
