package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Funcao;
import br.com.rh.model.Questao;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroQuestaoBean implements Serializable{
	private Questao questao;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	private Repositorios repositorios = new Repositorios();
	
	public CadastroQuestaoBean(){
		this.questao = new Questao();
	}
	
	@PostConstruct
	public void init(){
		Disciplinas disciplinas = this.repositorios.getDisciplinas();
		this.disciplinas = disciplinas.listarTodas();
		
		Funcoes funcoes = this.repositorios.getFuncoes();
		this.funcoes = funcoes.listarTodas();
	}

	public void cadastrar(){
		Questoes questoes = this.repositorios.getQuestoes();
		questoes.guardar(this.questao);
		
		this.questao = new Questao();
	}
	
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
		if(this.questao == null){
			this.questao = new Questao();
		}
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Repositorios getRepositorios() {
		return repositorios;
	}

	public void setRepositorios(Repositorios repositorios) {
		this.repositorios = repositorios;
	}

	
	
}
