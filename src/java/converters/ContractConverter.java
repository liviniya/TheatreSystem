/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converters;

import dao_interfaces.ContractDAO;
import entities.Contract;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Оксана
 */
@ManagedBean(name = "contractConverterBean")
@RequestScoped
@FacesConverter("converters.ContractConverter")
public class ContractConverter implements Converter {

    private ContractDAO jpaContractDAO = lookupJpaActorDAOLocal();
    
    /**
     * Creates a new instance of ContractConverter
     */
    public ContractConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.parseLong(value);
        return jpaContractDAO.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Contract contract = (Contract) value;
        return contract.getId().toString();
    }
    
    private ContractDAO lookupJpaActorDAOLocal() {
        try {
            Context c = new InitialContext();
            return (ContractDAO) c.lookup("java:global/TheatreSystem/JpaContractDAO!dao_interfaces.ContractDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
