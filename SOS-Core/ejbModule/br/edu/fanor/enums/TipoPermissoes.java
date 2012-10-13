package br.edu.fanor.enums;

public enum TipoPermissoes {

	INSERIR(1, "inserir"), 
	EDITAR(2, "editar"), 
	EXCLUIR(3, "excluir");

	private Integer value;

	private String nome;

	private TipoPermissoes(Integer value, String name) {
		this.setValue(value);
		this.setNome(name);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static TipoPermissoes getTipoPermissoes(Integer value) {
		for (TipoPermissoes tipoPermissoes : TipoPermissoes.values()) {
			if (tipoPermissoes.getValue() == value) {
				return tipoPermissoes;
			}
		}
		return null;
	};

	public static TipoPermissoes getTipoPermissoes(String name) {
		for (TipoPermissoes tipoPermissoes : TipoPermissoes.values()) {
			if (tipoPermissoes.getNome().equals(name)) {
				return tipoPermissoes;
			}
		}
		return null;
	};
}
