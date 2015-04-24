package br.com.rh.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
