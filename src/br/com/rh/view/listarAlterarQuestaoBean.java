package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.rh.model.Questao;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

/**
 * @author arley
 *
 */
@SuppressWarnings("serial")
@ManagedBean
public class listarAlterarQuestaoBean implements Serializable {
	private Questao  questaoSelecionada;
	private Repositorios repositorios = new Repositorios();
	private List<Questao> questoes = new ArrayList<Questao>();

	
	@PostConstruct
	public void init(){
		Questoes questoes = this.repositorios.getQuestoes();
		this.questoes = questoes.listarTodas();
	}
	
	public void excluir(){
		this.questoes.remove(this.questaoSelecionada);
		Questoes questoes = this.repositorios.getQuestoes();
		questoes.excluir(this.questaoSelecionada);
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Questao getQuestaoSelecionada() {
		return questaoSelecionada;
	}

	public void setQuestaoSelecionada(Questao questaoSelecionada) {
		this.questaoSelecionada = questaoSelecionada;
	}
	
	
	
	
}
