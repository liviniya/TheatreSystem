/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.WorkerDAO;
import entities.Worker;
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
@ManagedBean(name = "workerConverterBean")
@FacesConverter("converters.workerConverterBean")
public class WorkerConverter implements Converter {
    
    WorkerDAO jpaWorkerDAO = lookupJpaWorkerDAOLocal();    
    
    /**
     * Creates a new instance of ActorConverter
     */
    public WorkerConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaWorkerDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Worker worker = (Worker) value;
        return worker.getId().toString();
    }

    private WorkerDAO lookupJpaWorkerDAOLocal() {
        try {
            Context c = new InitialContext();
            return (WorkerDAO) c.lookup("java:global/TheatreSystem/JpaWorkerDAO!dao_interfaces.WorkerDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
