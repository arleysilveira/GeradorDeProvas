package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.rh.model.Funcao;
import br.com.rh.repository.Funcoes;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
public class ListarAlterarFuncaoBean implements Serializable{
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	private Funcao funcaoSelecionada;
	private Repositorios repositorios = new Repositorios();
	
	@PostConstruct
	public void init(){
		Funcoes funcoes = this.repositorios.getFuncoes();
		this.funcoes = funcoes.listarTodas();
	}
	
	public void excluir() {
		this.funcoes.remove(this.funcaoSelecionada);
		
		Funcoes funcao = this.repositorios.getFuncoes();
		funcao.excluir(this.funcaoSelecionada);
		FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Função Excluída com sucesso");
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Funcao getFuncaoSelecionada() {
		return funcaoSelecionada;
	}

	public void setFuncaoSelecionada(Funcao funcaoSelecionada) {
		this.funcaoSelecionada = funcaoSelecionada;
	}
	
	
	
	
}
