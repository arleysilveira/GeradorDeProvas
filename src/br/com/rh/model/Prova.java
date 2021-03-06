package br.com.rh.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Prova implements Serializable {
	private String numeroQuestoes;
	private Disciplina disciplinaSelecionada;
	private Funcao funcaoSelecionada;
	private String dificuldade;

	
	public String getNumeroQuestoes() {
		return numeroQuestoes;
	}
	public void setNumeroQuestoes(String numeroQuestoes) {
		this.numeroQuestoes = numeroQuestoes;
	}
	
	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}
	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}
	public Funcao getFuncaoSelecionada() {
		return funcaoSelecionada;
	}
	public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
		this.funcaoSelecionada = funcaoSelecionada;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	
	
}
