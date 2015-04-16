package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<Funcao> funcoes = new ArrayList<Funcao>();

	private String[] questoesFaceis = new String[3];
	private String[] questoesMedias = new String[3];
	private String[] questoesDifíceis = new String[3];
	private int[] verdFalso = new int[3];
	private int[] alternativas = new int[3];
	private int[] subjetivas = new int[3];
	
	

	private Repositorios repositorios = new Repositorios();
	
	private List<Questao> questoesSelecionadas = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas2 = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas3 = new ArrayList<Questao>();
	
	private Prova prova, prova2, prova3;
	private Questao questaoModificada, questaoSubjetiva;
	private Boolean subjetiva = false;
	
	

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
		Questoes questoes = repositorios.getQuestoes();
		this.questoesSelecionadas = questoes.listarPorDisciplina(
				questoesFaceis[0], questoesMedias[0], questoesDifíceis[0],
				prova.getDisciplinaSelecionada().getId().toString(), verdFalso[0], subjetivas[0],alternativas[0]);
		this.questoesSelecionadas2 = questoes.listarPorDisciplina(
				questoesFaceis[1], questoesMedias[1], questoesDifíceis[1],
				prova2.getDisciplinaSelecionada().getId().toString(), verdFalso[1], subjetivas[1],alternativas[1]);
		this.questoesSelecionadas3 = questoes.listarEspecificas(
				questoesFaceis[2], questoesMedias[2], questoesDifíceis[2],
				prova3.getFuncaoSelecionada().getId().toString(), verdFalso[2], subjetivas[2],alternativas[2]);

		System.out.println(questoesFaceis[0]+questoesMedias[0]+ questoesDifíceis[0]);
		
		return "Prova.xhtml?faces-redirect=true";
	}

	// Função para modificar a Questão
	public void modificarQuestao() {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas
				.indexOf(this.questaoModificada);

		this.questoesSelecionadas.set(posicaoQuestao,
				questoes.modificarQuestaoDisciplina(this.getQuestaoModificada().getDisciplina().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));
	}

	public void modificarQuestao2() {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas2
				.indexOf(this.questaoModificada);

		this.questoesSelecionadas2.set(posicaoQuestao,
				questoes.modificarQuestaoDisciplina(this.getQuestaoModificada().getDisciplina().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));
		
	}

	public void modificarQuestao3() {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas3
				.indexOf(this.questaoModificada);

		this.questoesSelecionadas3.set(posicaoQuestao,
				questoes.modificarQuestaoFuncao(this.getQuestaoModificada().getFuncao().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));
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


	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
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

	public Questao getQuestaoSubjetiva() {
		return questaoSubjetiva;
	}

	public void setQuestaoSubjetiva(Questao questaoSubjetiva) {
		this.questaoSubjetiva = questaoSubjetiva;
	}

	public Boolean getSubjetiva() {
		return subjetiva;
	}

	public void setSubjetiva(Boolean subjetiva) {
		this.subjetiva = subjetiva;
	}

	public String[] getQuestoesFaceis() {
		return questoesFaceis;
	}

	public void setQuestoesFaceis(String[] questoesFaceis) {
		this.questoesFaceis = questoesFaceis;
	}

	public String[] getQuestoesMedias() {
		return questoesMedias;
	}

	public void setQuestoesMedias(String[] questoesMedias) {
		this.questoesMedias = questoesMedias;
	}

	public String[] getQuestoesDifíceis() {
		return questoesDifíceis;
	}

	public void setQuestoesDifíceis(String[] questoesDifíceis) {
		this.questoesDifíceis = questoesDifíceis;
	}

	public int[] getVerdFalso() {
		return verdFalso;
	}

	public void setVerdFalso(int[] verdFalso) {
		this.verdFalso = verdFalso;
	}

	public int[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(int[] alternativas) {
		this.alternativas = alternativas;
	}

	public int[] getSubjetivas() {
		return subjetivas;
	}

	public void setSubjetivas(int[] subjetivas) {
		this.subjetivas = subjetivas;
	}

	
	
}
