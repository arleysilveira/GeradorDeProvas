package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.PaginaInformacoes;

public interface Paginas{
	
	public List<PaginaInformacoes> listarTodas();
	public PaginaInformacoes guardar(PaginaInformacoes pagina);
	
	public void excluir(PaginaInformacoes pagina);
	public PaginaInformacoes porCodigo(Integer codigo);

}
