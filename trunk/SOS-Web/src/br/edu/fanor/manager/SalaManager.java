package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.hibernate.collection.internal.PersistentBag;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.event.SelectEvent;

import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.entity.Sala;
import br.edu.fanor.enums.TipoSala;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.SalaService;
import br.edu.fanor.util.SalaDataModel;
import br.edu.fanor.util.SalaListener;

@SessionScoped
@ManagedBean
public class SalaManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -84971612047807691L;

	@EJB
	SalaService salaService;
	
	private List<Sala> listarSalas;
	
	private Sala sala = new Sala();
	private Integer tipoSala = 0;

	
	private SalaDataModel salasDisponiveis;
	private Date dia;
	private Date dataInicio;
	private Date dataFim;
	
	private List<TipoSala> tiposSala;
	
	private SalaListener salaListener;

	public void salvaSala() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		try {
			salaService.saveOrUpdate(sala);
			
			displayInfoMessageToUser("Sala " + sala.getNome() + " salvo com sucesso.");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/listaSalas.jsf");
			
			sala = new Sala();
			
		} catch (ValidacaoException e) {
			displayErrorMessageToUser("Erro ao tentar salvar ");
		}
		listarTodasSalas();
	}
	
	public String preparaEdicao(Sala sala){
		if (sala.getAcessorios() instanceof PersistentBag) {
			sala.setAcessorios(Arrays.asList(sala.getAcessorios().toArray(new Acessorio[sala.getAcessorios().size()])));
		}
		this.sala = sala;
		return "cadastroSala";
	}
	
	public String preparaCadastro(){
		sala = new Sala();
		return "cadastroSala";
	}
	
	public void configurarBusca(Date inicio, Date fim){
		setDataInicio(inicio);
		setDataFim(fim);
		setDia(dataInicio);
		listarSalasDisponiveis();
	}
	
	public void deletar(Sala sala){
		try {
			salaService.delete(sala);
			displayInfoMessageToUser("Sala"+ sala.getNome()+ " excluida com sucesso.");
		} catch (Exception e) {
			displayErrorMessageToUser("Nao foi possivel excluir a sala");
		}
		listarTodasSalas();
	}
	
	public void validarHora(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    
		Date inicio = (Date) ((Calendar) component.findComponent("inicio")).getValue();
		Date fim = (Date) value;
		
		
        if(inicio.after(fim)) {
            FacesMessage message = new FacesMessage();	
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Time Error.");
            message.setDetail("A hora inicial tem que ser anterior a hora final");
            context.addMessage("pesqSalaDialogForm:inicio", message);
            throw new ValidatorException(message);
        }
    
	}
	
	public Date currentDate(){
		return new Date();
	}
	
	public void listarTodasSalas() {
		listarSalas = salaService.listarTodas();
	}
	
	public void listarSalasDisponiveis(ActionEvent event){
		listarSalasDisponiveis();
	}
	
	public void listarSalasDisponiveis(){
		if (dataInicio == null || dataFim == null || dia == null) {
			salasDisponiveis = new SalaDataModel(new ArrayList<Sala>());
		}else {
			dataInicio = synchronizeDate(dataInicio, dia);
			dataFim = synchronizeDate(dataFim, dia);
			salasDisponiveis = new SalaDataModel(salaService.listarSalasDisponiveis(dataInicio, dataFim));
		}
		
	}
	
	public void onRowSelect(SelectEvent event) {
		salaListener.selecionaSalaDisponivel((Sala) event.getObject());
    }
	
	public void findByQtd(){
		listarSalas =  salaService.findByQtd(sala.getCapacidade());
	}
	
	public void findByNome(){
		listarSalas = salaService.findByNome(sala.getNome());
	}
	
	public List<Sala> getListarSalas(){
		if (listarSalas == null){
			return listarSalas = salaService.listarTodas();
		} else {
			return listarSalas;
		}
	}
	
	public Sala getSala() {
		if(sala == null){
			return new Sala();
		}else{
			return sala;
		}
	}

	public List<TipoSala> getTiposSala() {
		if (tiposSala == null) {
			tiposSala = Arrays.asList(TipoSala.values());
		}
		return tiposSala; 
	}
	
	public void setTiposSala(List<TipoSala> tiposSala) {
		this.tiposSala = tiposSala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Integer getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(Integer tipoSala) {
		this.tipoSala = tipoSala;
	}
	
	public SalaListener getSalaListener() {
		if (salaListener == null) {
			salaListener = new SalaListener() {
				
				@Override
				public void selecionaSalaDisponivel(Sala sala) {
					System.out.println("vc deve setar o salaListener");
				}
			};
		}
		return salaListener;
	}

	public void setSalaListener(SalaListener salaListener) {
		this.salaListener = salaListener;
	}
	
	public SalaDataModel getSalasDisponiveis() {
		return salasDisponiveis;
	}

	public void setSalasDisponiveis(SalaDataModel salasDisponiveis) {
		this.salasDisponiveis = salasDisponiveis;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}
