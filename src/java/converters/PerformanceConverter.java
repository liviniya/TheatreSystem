/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.PerformanceDAO;
import entities.Performance;
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
@ManagedBean(name = "performanceConverterBean")
@FacesConverter("converters.performanceConverterBean")
public class PerformanceConverter implements Converter {
    PerformanceDAO jpaPerformanceDAO = lookupJpaPerformanceDAOLocal();
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaPerformanceDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Performance performance = (Performance) value;        
        return performance.getId().toString();
    }

    private PerformanceDAO lookupJpaPerformanceDAOLocal() {
        try {
            Context c = new InitialContext();
            return (PerformanceDAO) c.lookup("java:global/TheatreSystem/JpaPerformanceDAO!dao_interfaces.PerformanceDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
