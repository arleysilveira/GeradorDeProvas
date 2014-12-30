package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Cargo;
import br.com.rh.model.Funcao;
import br.com.rh.util.FacesUtil;

@ManagedBean
public class CadastroFuncaoBean {
	private List<Cargo> cargos = new ArrayList<Cargo>();
	private Funcao funcao;
	private Session session;
	
	public CadastroFuncaoBean(){
		this.funcao = new Funcao();
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		this.session = (Session) FacesUtil.getRequestAttribute("session");
		
		this.cargos = session.createCriteria(Cargo.class)
				.addOrder(Order.asc("nome"))
				.list();
	}
	
	
	public void cadastrar() {
		if (this.funcao.getNome() == "") {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Campo Obrigatório",
					"Favor informar o nome da função");
		} else if (this.funcao.getNome().length() < 5) {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Nome incompleto",
					"A função deve possuir no mínimo 5 letras");
		} else {
			this.session = (Session) FacesUtil.getRequestAttribute("session");
			session.merge(this.funcao);

			this.funcao = new Funcao();
			FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro concluído");
		}
	}
	
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	
	
}
