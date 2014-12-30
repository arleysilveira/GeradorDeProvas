package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Cargo;
import br.com.rh.model.Disciplina;
import br.com.rh.model.Questao;
import br.com.rh.util.FacesUtil;

@ManagedBean
public class CadastroQuestaoBean {
	private Questao questao;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Session session;
	
	public CadastroQuestaoBean(){
		this.questao = new Questao();
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		this.session = (Session) FacesUtil.getRequestAttribute("session");		
		
		this.disciplinas = session.createCriteria(Disciplina.class)
				.addOrder(Order.asc("nome"))
				.list();
		
		this.cargos = session.createCriteria(Cargo.class)
				.addOrder(Order.asc("nome"))
				.list();
		
	}

	public void cadastrar(){
		this.session = (Session) FacesUtil.getRequestAttribute("session");
		session.merge(this.questao);
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

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
	
}
