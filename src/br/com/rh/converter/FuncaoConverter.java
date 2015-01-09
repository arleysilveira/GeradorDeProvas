package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rh.model.Funcao;
import br.com.rh.repository.Funcoes;
import br.com.rh.util.Repositorios;

@FacesConverter(forClass=Funcao.class)
public class FuncaoConverter implements Converter {
	
	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Funcao retorno = null;
		
		if(value != null){
			Funcoes funcoes = repositorios.getFuncoes();
			retorno = funcoes.porCodigo(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Funcao) value).getId().toString();
		}
		return null;
	}

}
