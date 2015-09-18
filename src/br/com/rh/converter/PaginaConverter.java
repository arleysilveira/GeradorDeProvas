package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rh.model.PaginaInformacoes;
import br.com.rh.repository.Paginas;
import br.com.rh.util.Repositorios;

@FacesConverter(forClass=PaginaInformacoes.class)
public class PaginaConverter implements Converter {


	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
	PaginaInformacoes retorno = null;
		
		if(value != null && !value.equals("")){
			Paginas paginas = repositorios.getPaginas();
			retorno = paginas.porCodigo(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Integer codigo = ((PaginaInformacoes) value).getId();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}
	
}
