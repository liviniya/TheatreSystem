/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.RealPlayedRoleDAO;
import entities.RealPlayedRole;
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
@ManagedBean(name = "realRoleConverterBean")
@FacesConverter("converters.realRoleConverterBean")
public class RealRoleConverter implements Converter {
    
    RealPlayedRoleDAO jpaRealPlayedRoleDAO = lookupJpaActorDAOLocal();
    
    /**
     * Creates a new instance of ActorConverter
     */
    public RealRoleConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaRealPlayedRoleDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        RealPlayedRole role = (RealPlayedRole) value;
        return role.getId().toString();
    }

    private RealPlayedRoleDAO lookupJpaActorDAOLocal() {
        try {
            Context c = new InitialContext();
            return (RealPlayedRoleDAO) c.lookup("java:global/TheatreSystem/JpaRealPlayedRoleDAO!dao_interfaces.RealPlayedRoleDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
