package br.edu.fanor.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

	//TODO Tratar ValidatorException

	@Override 
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		
		String email = (String) value;
		 
		 if(!email.contains("@") || !email.contains(".")) {
			FacesMessage msg =
					new FacesMessage("Formato de Email inválido", "Exemplo: user@exemplo.com");		
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
	     }

	}
}
