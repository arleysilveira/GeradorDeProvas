package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.Questao;

public interface Questoes {

	public List<Questao> listarTodas();
	public List<Questao> listarEspecificas(String consulta, String numeroDeQuestoes);
	public Questao porCodigo(Integer codigo);
	public Questao selecionarQuestaoSubjetiva(String consulta);
	
	public Questao guardar(Questao questao);
	public void excluir(Questao questao);
}
