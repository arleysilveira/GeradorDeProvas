package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Disciplinas;
import br.com.rh.util.HibernateUtil;

@ManagedBean
public class GerarProvaBean {
	private List<Disciplinas> disciplinas = new ArrayList<Disciplinas>();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		Session session = HibernateUtil.getsessionSession();
		
		this.disciplinas = session.createCriteria(Disciplinas.class)
				.addOrder(Order.asc("nome"))
				.list();
		
		session.close();
	}
	
	public List<Disciplinas> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplinas> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
}
