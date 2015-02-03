package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rh.model.Questao;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

@FacesConverter(forClass=Questao.class)
public class QuestaoConverter implements Converter{
	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Questao retorno = null;
		
		if(value !=null && !value.equals("")){
			Questoes questoes = repositorios.getQuestoes();
			retorno = questoes.porCodigo(new Integer(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!=null){
			Integer codigo = ((Questao) value).getId();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}
