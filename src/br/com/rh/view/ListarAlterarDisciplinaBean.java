package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;

import br.com.rh.model.Disciplina;
import br.com.rh.repository.Disciplinas;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@ManagedBean
public class ListarAlterarDisciplinaBean {
	private Repositorios repositorios = new Repositorios();
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private Disciplina disciplinaSelecionada;
	private Session session;
	
	@PostConstruct
	public void init(){
		Disciplinas disciplinas = this.repositorios.getDisciplinas();
		this.disciplinas = disciplinas.listarTodas();
		
	}
	
	public void excluir(){
		this.disciplinas.remove(disciplinaSelecionada);
		
		this.session = (Session) FacesUtil.getRequestAttribute("session");
		
		session.delete(this.disciplinaSelecionada);
		
		FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Disciplina Excluída com sucesso");
		
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}
	
	
}
