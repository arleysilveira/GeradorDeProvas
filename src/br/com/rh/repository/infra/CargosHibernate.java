package br.com.rh.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Cargo;
import br.com.rh.repository.Cargos;

public class CargosHibernate implements Cargos{

	private Session session;
	
	public CargosHibernate(Session session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> listarTodos() {
		return this.session.createCriteria(Cargo.class).addOrder(Order.asc("nome")).list();
	}

}
