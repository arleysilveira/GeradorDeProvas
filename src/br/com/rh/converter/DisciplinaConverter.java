package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rh.model.Disciplina;
import br.com.rh.repository.Disciplinas;
import br.com.rh.util.Repositorios;

@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Disciplina retorno = null;

		if (value != null && !value.equals("")) {
			Disciplinas disciplinas = repositorios.getDisciplinas();
			retorno = disciplinas.porCodigo(new Integer(value));
			
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Integer codigo = ((Disciplina) value).getId();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}
