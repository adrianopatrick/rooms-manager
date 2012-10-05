package br.edu.fanor.exceptions;


public class ValidacaoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ValidacaoException() {
		super();
	}
	
	public ValidacaoException(String mensagem){
		super(mensagem);
	}
	
}
