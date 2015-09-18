package br.com.rh.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.rh.model.PaginaInformacoes;
import br.com.rh.repository.Paginas;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@ManagedBean
public class ListarAlterarPaginasBean {
	private Repositorios repositorios = new Repositorios();
	private List<PaginaInformacoes> paginas = new ArrayList<PaginaInformacoes>();
	private PaginaInformacoes paginaSelecionada;
	
	
	@PostConstruct
	public void init() {
		Paginas paginas = this.repositorios.getPaginas();
		this.paginas = paginas.listarTodas();
	}
	
	public void excluir(){
		Paginas paginas = this.repositorios.getPaginas();
		paginas.excluir(this.paginaSelecionada);
		FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Disciplina Excluída com sucesso");
		init();
		
	}

	public List<PaginaInformacoes> getPaginas() {
		return paginas;
	}
	

	public void setPaginas(List<PaginaInformacoes> paginas) {
		this.paginas = paginas;
	}
	

	public PaginaInformacoes getPaginaSelecionada() {
		return paginaSelecionada;
	}
	

	public void setPaginaSelecionada(PaginaInformacoes paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}
	
	
	
	
}
