package br.edu.fanor.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.edu.fanor.entity.Sala;

public class SalaDataModel extends ListDataModel<Sala> implements SelectableDataModel<Sala>{

	 public SalaDataModel() {  
	    }  
	  
	    public SalaDataModel(List<Sala> data) {  
	        super(data);  
	    }  
	      
	    @Override  
	    @SuppressWarnings("unchecked")
	    public Sala getRowData(String rowKey) {  
	          
	        List<Sala> Salas = (List<Sala>) getWrappedData();  
	          
	        for(Sala Sala : Salas) {  
	            if(Sala.getId().equals(Long.parseLong(rowKey)))  
	                return Sala;  
	        }  
	          
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(Sala Sala) {  
	        return Sala.getId();  
	    }  

}
