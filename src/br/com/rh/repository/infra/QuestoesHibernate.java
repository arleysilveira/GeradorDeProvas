package br.com.rh.repository.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Questao> listarEspecificas(String consulta, Integer numeroDeQuestoes) {
		Query query = session.createQuery(consulta);
		//query.setFirstResult(1);
		List<Questao> teste = new ArrayList<Questao>();
		teste = query.list();
		Collections.shuffle(teste);
		query.setMaxResults(numeroDeQuestoes);
		
		return teste;
		
		
	}
	
}
