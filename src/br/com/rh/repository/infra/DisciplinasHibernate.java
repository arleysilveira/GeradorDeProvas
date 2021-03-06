package br.com.rh.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Disciplina;
import br.com.rh.repository.Disciplinas;

public class DisciplinasHibernate implements Disciplinas {
	private Session session;
	
	public DisciplinasHibernate(Session session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> listarTodas() {
		
		return session.createCriteria(Disciplina.class)
				.addOrder(Order.asc("nome"))
				.list();
	}

	@Override
	public Disciplina porCodigo(Integer codigo) {
		return (Disciplina) session.get(Disciplina.class, codigo);
	}

	@Override
	public Disciplina guardar(Disciplina disciplina) {
		return (Disciplina) this.session.merge(disciplina);
	}

	@Override
	public void excluir(Disciplina disciplina) {
		this.session.delete(disciplina);
	}

	
	
}
