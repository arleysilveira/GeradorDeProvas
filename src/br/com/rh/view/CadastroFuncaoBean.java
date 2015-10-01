package br.com.rh.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rh.model.Cargo;
import br.com.rh.model.Funcao;
import br.com.rh.repository.Cargos;
import br.com.rh.repository.Funcoes;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroFuncaoBean implements Serializable{
	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Funcao funcao;
	private Repositorios repositorios = new Repositorios();
	
	public CadastroFuncaoBean(){
		this.funcao = new Funcao();
	}
	
	@PostConstruct
	public void init(){
		Cargos cargos = this.repositorios.getCargos();
		this.cargos = cargos.listarTodos();
	}
	
	
	public void cadastrar() {
		if (this.funcao.getNome() == "") {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Campo Obrigatório",
					"Favor informar o nome da função");
		} else if (this.funcao.getNome().length() < 5) {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Nome incompleto",
					"A função deve possuir no mínimo 5 letras");
		} else {
			if(this.funcao.getId() != null){
				FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Alteração concluída com sucesso");
			} else {
				FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro concluído com sucesso");
			}
			Funcoes funcao = this.repositorios.getFuncoes();
			funcao.guardar(this.funcao);

			this.funcao = new Funcao();
		}
	}
	
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
		if(this.funcao == null){
			this.funcao = new Funcao();
		}
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
	
}
