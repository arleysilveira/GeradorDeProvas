package br.com.rh.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.rh.model.PaginaInformacoes;
import br.com.rh.repository.Paginas;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@ManagedBean
public class CadastroPaginaInformacoesBean {

	private PaginaInformacoes pagina;
	private Repositorios repositorios = new Repositorios();
	
	public CadastroPaginaInformacoesBean(){
		this.pagina = new PaginaInformacoes();
	}
	
	public void cadastrar() {
		Paginas paginas = this.repositorios.getPaginas();
		paginas.guardar(this.pagina);
		
		this.pagina = new PaginaInformacoes();
		FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro concluído");
	}

	public PaginaInformacoes getPagina() {
		return pagina;
	}

	public void setPagina(PaginaInformacoes pagina) {
		this.pagina = pagina;
		if(this.pagina == null){
			this.pagina = new PaginaInformacoes();
		}
	}
	
	
	
}
