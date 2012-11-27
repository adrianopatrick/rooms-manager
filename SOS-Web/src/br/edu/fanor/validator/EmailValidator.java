package br.edu.fanor.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {
	
	//TODO Tratar ValidatorException... 
	/*
	 * o validador Exception não precisa ser tratado 
	 * como vc pode ver o metodo validate ja lança essa exeption pois
	 * é quando ela for lancada que o campo nao vai ter passado na validacao,
	 * quem faz o tratamento desse exception é o jsf.
	 * 
	 * obj: retirar TODO_ e comentario ao ver esse isso.
	 */
	@Override 
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
	
		String email = (String) value;
		
		if(!email.contains("@") || !email.substring(email.indexOf("@")).contains(".") ) {
			 FacesMessage msg =
						new FacesMessage("Formato de Email invÃ¡lido", "Exemplo: user@exemplo.com");		
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg); 
	     }

	}
}
