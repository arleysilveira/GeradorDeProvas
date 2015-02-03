package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.rh.model.Questao;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

@ManagedBean
public class ProvaBean {
	private List<Questao> questoes = new ArrayList<Questao>();
	private Repositorios Repositorios = new Repositorios();
	
	
	
	
	
	public ProvaBean() {
		
		 
			
		System.out.println();
	}
	
	@PostConstruct
	public void init(){
		Questoes questoes = Repositorios.getQuestoes();
		this.questoes = questoes.listarTodas();
	}
	
	public List<Questao> getQuestoes() {
		return questoes;
	}
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
	
}
