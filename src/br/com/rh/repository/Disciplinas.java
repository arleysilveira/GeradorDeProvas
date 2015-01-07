package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.Disciplina;

public interface Disciplinas {

	public List<Disciplina> listarTodas();
	public Disciplina porCodigo(Integer codigo);
	
	public Disciplina guardar(Disciplina disciplina);
	public void excluir(Disciplina disciplina);
	
}
