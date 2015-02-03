package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Funcao;
import br.com.rh.model.Prova;
import br.com.rh.model.Questao;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class GerarProvaBean implements Serializable {
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Questao> questoes;
	private List<Questao> questoes2 = new ArrayList<Questao>();
	private List<Questao> questoes3 = new ArrayList<Questao>();
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	private Repositorios repositorios = new Repositorios();
	private List<Questao> questoesSelecionadas = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas2 = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas3 = new ArrayList<Questao>();
	private Prova prova, prova2, prova3;
	private Questao questaoModificada;

	public GerarProvaBean() {
		this.prova = new Prova();
		this.prova2 = new Prova();
		this.prova3 = new Prova();
	}

	@PostConstruct
	public void init() {
		Disciplinas disciplinas = repositorios.getDisciplinas();
		this.disciplinas = disciplinas.listarTodas();

		Funcoes funcoes = repositorios.getFuncoes();
		this.funcoes = funcoes.listarTodas();

	}

	public String listarQuestoes() {
		this.questoes = new ArrayList<Questao>();
		this.questoes.clear();
		Questoes questoes = repositorios.getQuestoes();

		String consulta = "from Questao Q where disciplina_id="
				+ this.prova.getDisciplinaSelecionada().getId().toString();
		this.questoes = questoes.listarEspecificas(consulta,
				(Integer.parseInt(this.prova.getNumeroQuestoes())));

		String consulta1 = "from Questao Q where disciplina_id="
				+ this.prova2.getDisciplinaSelecionada().getId().toString();
		this.questoes2 = questoes.listarEspecificas(consulta1,
				(Integer.parseInt(this.prova2.getNumeroQuestoes())));

		String consulta2 = "from Questao Q where id_funcao="
				+ this.prova3.getFuncaoSelecionada().getId().toString();
		this.questoes3 = questoes.listarEspecificas(consulta2,
				(Integer.parseInt(this.prova3.getNumeroQuestoes())));

		questoesSelecionadas.clear();
		questoesSelecionadas2.clear();
		questoesSelecionadas3.clear();

		for (int i = 0; i < Integer.parseInt(this.prova.getNumeroQuestoes()); i++) {
			questoesSelecionadas.add(this.questoes.get(i));
		}

		for (int i = 0; i < Integer.parseInt(this.prova2.getNumeroQuestoes()); i++) {
			questoesSelecionadas2.add(this.questoes2.get(i));
		}

		for (int i = 0; i < Integer.parseInt(this.prova3.getNumeroQuestoes()); i++) {
			questoesSelecionadas3.add(this.questoes3.get(i));
		}

		return "Prova.xhtml?faces-redirect=true";
	}

	public void modificarQuestao(){
		int posicaoQuestao = this.questoesSelecionadas.indexOf(this.questaoModificada);
		Random r = new Random();
		final int H = this.questoes.size();
		final int L = 0 ;
		int numeroSorteado = (int)( r.nextDouble() * ( H-L )) + L;
		this.questoesSelecionadas.set(posicaoQuestao, this.questoes.get(numeroSorteado));
	}
	
	public void modificarQuestao2(){
		int posicaoQuestao = this.questoesSelecionadas2.indexOf(this.questaoModificada);
		Random r = new Random();
		final int H = this.questoes2.size();
		final int L = 0 ;
		int numeroSorteado = (int)( r.nextDouble() * ( H-L )) + L;
		this.questoesSelecionadas2.set(posicaoQuestao, this.questoes2.get(numeroSorteado));
	}
	
	public void modificarQuestao3(){
		int posicaoQuestao = this.questoesSelecionadas3.indexOf(this.questaoModificada);
		Random r = new Random();
		final int H = this.questoes3.size();
		final int L = 0 ;
		int numeroSorteado = (int)( r.nextDouble() * ( H-L )) + L;
		this.questoesSelecionadas3.set(posicaoQuestao, this.questoes3.get(numeroSorteado));
	}

	// Getters and Setters
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public List<Questao> getQuestoes2() {
		return questoes2;
	}

	public void setQuestoes2(List<Questao> questoes2) {
		this.questoes2 = questoes2;
	}

	public Prova getProva2() {
		return prova2;
	}

	public void setProva2(Prova prova2) {
		this.prova2 = prova2;
	}

	public Prova getProva3() {
		return prova3;
	}

	public void setProva3(Prova prova3) {
		this.prova3 = prova3;
	}

	public List<Questao> getQuestoes3() {
		return questoes3;
	}

	public void setQuestoes3(List<Questao> questoes3) {
		this.questoes3 = questoes3;
	}

	public List<Questao> getQuestoesSelecionadas() {
		return questoesSelecionadas;
	}

	public void setQuestoesSelecionadas(List<Questao> questoesSelecionadas) {
		this.questoesSelecionadas = questoesSelecionadas;
	}

	public List<Questao> getQuestoesSelecionadas2() {
		return questoesSelecionadas2;
	}

	public void setQuestoesSelecionadas2(List<Questao> questoesSelecionadas2) {
		this.questoesSelecionadas2 = questoesSelecionadas2;
	}

	public List<Questao> getQuestoesSelecionadas3() {
		return questoesSelecionadas3;
	}

	public void setQuestoesSelecionadas3(List<Questao> questoesSelecionadas3) {
		this.questoesSelecionadas3 = questoesSelecionadas3;
	}

	public Questao getQuestaoModificada() {
		return questaoModificada;
	}

	public void setQuestaoModificada(Questao questaoModificada) {
		this.questaoModificada = questaoModificada;
	}

}
