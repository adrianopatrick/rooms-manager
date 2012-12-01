package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.edu.fanor.enums.TipoSala;

@Entity(name = "salas")
public class Sala {

	@Id
	@SequenceGenerator(sequenceName = "public.seq_salas", name = "seq_salas", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_salas")
	private Long id;

	@NotNull
	@Column(unique = true)
	private String nome;

	@NotNull
	private Integer capacidade;

	@NotNull
	private Integer tipoSala;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "acessorios_salas", inverseJoinColumns = @JoinColumn(name = "id_acessorio"), joinColumns = @JoinColumn(name = "id_sala"))
	private List<Acessorio> acessorios;

	@NotNull
	private String observacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getId() {
		return id;
	}

	public TipoSala getTipoSala() {
		if (tipoSala != null) {
			return TipoSala.get(tipoSala);
		}
		return null;
	}

	public void setTipoSala(TipoSala tipoSala) {
		if (tipoSala == null) {
			this.tipoSala = null;
		} else {
			this.tipoSala = tipoSala.getKey();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
