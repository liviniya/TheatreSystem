/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.RoleDAO;
import entities.Role;
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
@ManagedBean(name = "roleConverterBean")
@FacesConverter("converters.roleConverter")
public class RoleConverter implements Converter {
    RoleDAO jpaRoleDAO = lookupJpaRoleDAOLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaRoleDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Role role = (Role) value;
        return role.getId().toString();
    }

    private RoleDAO lookupJpaRoleDAOLocal() {
        try {
            Context c = new InitialContext();
            return (RoleDAO) c.lookup("java:global/TheatreSystem/JpaRoleDAO!dao_interfaces.RoleDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
