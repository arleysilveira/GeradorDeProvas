package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Disciplina;
import br.com.rh.util.HibernateUtil;

@ManagedBean
public class GerarProvaBean {
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		Session session = HibernateUtil.getsessionSession();
		
		this.disciplinas = session.createCriteria(Disciplina.class)
				.addOrder(Order.asc("nome"))
				.list();
		
		session.close();
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
}
