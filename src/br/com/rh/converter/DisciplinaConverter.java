package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import br.com.rh.model.Disciplinas;
import br.com.rh.util.HibernateUtil;

@FacesConverter(forClass=Disciplinas.class)
public class DisciplinaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Disciplinas retorno = null;
		
		if(value != null){
		Session session = HibernateUtil.getsessionSession();
		
		retorno = (Disciplinas) session.get(Disciplinas.class, new Integer(value));
		
		session.close();
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Disciplinas) value).getId().toString();
		}
		return null;
	}

}
