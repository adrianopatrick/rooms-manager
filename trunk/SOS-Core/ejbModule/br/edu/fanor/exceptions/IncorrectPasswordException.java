package br.edu.fanor.exceptions;

public class IncorrectPasswordException extends DefaultException{

	private static final long serialVersionUID = -5612108719759679235L;

	public IncorrectPasswordException() {
		super();
	}
	
	public IncorrectPasswordException(String msg) {
		super(msg);
	}
	
}
