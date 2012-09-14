package br.edu.fanor.manager;

public class Teste{
	 
	 private String nomeEvento;
	 private String nome;
	 private int capacidade; 
	 private String tpSala;
	 private String acessorio;
	 
	 public Teste(String nomeEvento, String nome, int capacidade, String tpSala, String acessorio){
		 this.nomeEvento = nomeEvento;
		
	 }

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getTpSala() {
		return tpSala;
	}

	public void setTpSala(String tpSala) {
		this.tpSala = tpSala;
	}

	public String getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(String acessorio) {
		this.acessorio = acessorio;
	}
}