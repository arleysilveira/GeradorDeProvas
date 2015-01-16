package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.Questao;

public interface Questoes {

	public List<Questao> listarTodas();
	public Questao porCodigo(Integer codigo);
	
	public Questao guardar(Questao questao);
	public void excluir(Questao questao);
}
