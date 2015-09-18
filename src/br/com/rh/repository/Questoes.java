package br.com.rh.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import br.com.rh.model.Questao;

public interface Questoes {

	public List<Questao> listarTodas();

	public List<Questao> listarEspecificas(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis,
			String funcao, int numVerdFalsa, int numSubj, int numAlter);

	public Questao porCodigo(Integer codigo);

	public Questao selecionarQuestaoSubjetiva(String consulta);

	public List<Questao> listarPorDisciplina(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis, String disciplina,
			int numVerdFalsa, int numSubj, int numAlter);

	public Questao guardar(Questao questao);

	public void excluir(Questao questao);
	
	public Questao modificarQuestaoDisciplina(String idDisciplina,
			String dificuldade, int idQuestao);
	
	public Questao modificarQuestaoFuncao(String idFuncao,
			String dificuldade, int idQuestao);
	
}
