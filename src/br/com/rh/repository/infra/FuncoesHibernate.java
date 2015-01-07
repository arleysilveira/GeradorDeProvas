package br.com.rh.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Funcao;
import br.com.rh.repository.Funcoes;

public class FuncoesHibernate implements Funcoes{

	private Session session;
	
	public FuncoesHibernate(Session session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcao> listarTodas() {
		return session.createCriteria(Funcao.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Funcao guardar(Funcao funcao) {
		this.session.merge(funcao);
		return null;
	}

	@Override
	public void excluir(Funcao funcao) {
		this.session.delete(funcao);		
	}

	@Override
	public Funcao porCodigo(Integer codigo) {
		return (Funcao) this.session.get(Funcao.class, codigo);
	}

	
	
}
