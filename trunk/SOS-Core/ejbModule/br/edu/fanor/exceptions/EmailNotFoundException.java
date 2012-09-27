package br.edu.fanor.exceptions;

public class EmailNotFoundException extends DefaultException{

	private static final long serialVersionUID = -6890425285925358192L;
	
	public EmailNotFoundException(String msg) {
		super(msg);
	}
	
	public EmailNotFoundException() {
		super();
	
	}
}
