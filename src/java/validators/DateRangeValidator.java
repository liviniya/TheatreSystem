/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validators;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Oksana_Moroz
 */
@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        Object startDateValue = component.getAttributes().get("startDate");
        if (startDateValue == null) {
            return;
        }
        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    context.getApplication().getResourceBundle(context, "msg")
                            .getString("startEndDate"), null));
        }
    }
    
}
