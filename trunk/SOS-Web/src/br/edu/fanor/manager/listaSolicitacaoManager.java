package br.edu.fanor.manager;

import br.edu.fanor.manager.Teste;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("unused")
@RequestScoped
@ManagedBean(name="listaTeste")
public class listaSolicitacaoManager {
	
	private List<Teste> lista = new ArrayList<Teste>();
	
	public List<Teste> getLista() {
		
		Teste t1 = new Teste("Lab. 1", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t2 = new Teste("Lab. 2", "Lab 2", 40, "Sala", "Apagador");
		Teste t3 = new Teste("Revisao", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t4 = new Teste("Aula extra Mat.", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t5 = new Teste("Curso Java", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t6 = new Teste("Curso Java", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t7 = new Teste("Curso Java", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t8 = new Teste("Curso Java", "Lab 1", 40, "Laboratorio", "Lixeira");
		Teste t9 = new Teste("Curso Java", "Lab 1", 40, "Laboratorio", "Lixeira");
		
		lista.add(t1);
		lista.add(t2);
		lista.add(t3);
		lista.add(t4);
		lista.add(t5);
		lista.add(t6);
		lista.add(t7);
		lista.add(t8);
		lista.add(t9);
		
		return lista;
	}
}