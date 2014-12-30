package br.com.rh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import br.com.rh.model.Cargo;
import br.com.rh.util.HibernateUtil;

@FacesConverter(forClass=Cargo.class)
public class CargoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cargo retorno = null;
		
		if(value != null){
		Session session = HibernateUtil.getsessionSession();
		
		retorno = (Cargo) session.get(Cargo.class, new Integer(value));
		
		session.close();
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Cargo) value).getId().toString();
		}
		return null;
	}

}
