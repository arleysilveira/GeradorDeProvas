package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Questao;
import br.com.rh.util.HibernateUtil;

@ManagedBean
public class CadastroQuestaoBean {
	private Questao questao;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public CadastroQuestaoBean(){
		this.questao = new Questao();
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		Session session = HibernateUtil.getsessionSession();
		
		this.disciplinas = session.createCriteria(Disciplina.class)
				.addOrder(Order.asc("nome"))
				.list();
		
		
		session.close();
	}

	public void cadastrar(){
		
		Session session = HibernateUtil.getsessionSession();
		Transaction trx = session.beginTransaction();
		
		session.merge(this.questao);
		
		trx.commit();
		session.close();
	}
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
	
}
