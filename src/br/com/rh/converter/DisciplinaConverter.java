package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import br.com.rh.model.Disciplina;
import br.com.rh.util.HibernateUtil;

@FacesConverter(forClass=Disciplina.class)
public class DisciplinaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Disciplina retorno = null;
		
		if(value != null){
		Session session = HibernateUtil.getsessionSession();
		
		retorno = (Disciplina) session.get(Disciplina.class, new Integer(value));
		
		session.close();
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Disciplina) value).getId().toString();
		}
		return null;
	}

}
