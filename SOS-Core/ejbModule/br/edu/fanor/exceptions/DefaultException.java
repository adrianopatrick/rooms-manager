package br.edu.fanor.exceptions;

public class DefaultException extends Exception{

	private static final long serialVersionUID = -4041626472045904502L;
	
	protected String msg;
	
	public DefaultException() {
		super();
	}
	
	public DefaultException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
