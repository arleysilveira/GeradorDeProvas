package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.Funcao;

public interface Funcoes {
	
	public List<Funcao> listarTodas();
	public Funcao guardar(Funcao funcao);
	
	public void excluir(Funcao funcao);
	public Funcao porCodigo(Integer codigo);
	
}
