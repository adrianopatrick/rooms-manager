package br.edu.fanor.enums;

public enum TipoSala {

	SALA		(1, "Sala"),
	LABORATORIO	(2, "Laboratório"),
	AUDITORIO	(3, "Auditório"),
	ATELIE		(4, "Ateliê");

	private Integer key;
	
	private String label;
	
	private TipoSala(Integer key, String label){
		this.setKey(key);
		this.setLabel(label);
	}

	public static TipoSala get(Integer key){
		
		for (TipoSala tipoSala : values()) {
			if (tipoSala.getKey().equals(key)) {
				return tipoSala;
			}
		}
		return null;
	}
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
