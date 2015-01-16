package br.com.rh.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Questao;
import br.com.rh.repository.Questoes;

public class QuestoesHibernate implements Questoes{

	private Session session;
	
	public QuestoesHibernate(Session session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questao> listarTodas() {
		return session.createCriteria(Questao.class).addOrder(Order.asc("titulo")).list();
	}

	@Override
	public Questao guardar(Questao questao) {
		return (Questao) session.merge(questao);
	}

	@Override
	public void excluir(Questao questao) {
		this.session.delete(questao);
	}

	@Override
	public Questao porCodigo(Integer codigo) {
		return (Questao) session.get(Questao.class, codigo);

	}
	
}
