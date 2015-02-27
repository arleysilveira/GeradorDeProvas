package br.com.rh.repository;

import java.util.List;

import br.com.rh.model.Questao;

public interface Questoes {

	public List<Questao> listarTodas();

	public List<Questao> listarEspecificas(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis,
			String funcao);

	public Questao porCodigo(Integer codigo);

	public Questao selecionarQuestaoSubjetiva(String consulta);

	public List<Questao> listarPorDisciplina(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis, String disciplina);

	public Questao guardar(Questao questao);

	public void excluir(Questao questao);
	
	public Questao modificarQuestaoDisciplina(String disciplina,
			String dificuldade, int idQuestao);
}
