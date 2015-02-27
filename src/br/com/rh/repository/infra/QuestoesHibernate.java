package br.com.rh.repository.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.Questao;
import br.com.rh.repository.Questoes;

public class QuestoesHibernate implements Questoes {

	private Session session;

	public QuestoesHibernate(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questao> listarTodas() {
		return session.createCriteria(Questao.class)
				.addOrder(Order.asc("titulo")).list();
	}

	@Override
	public Questao guardar(Questao questao) {
		return (Questao) session.merge(questao);
	}

	@Override
	public void excluir(Questao questao) {
		this.session.delete(questao);
	}

	@Override
	public Questao porCodigo(Integer codigo) {
		return (Questao) session.get(Questao.class, codigo);

	}

	// Gera uma lista das questões especificadas pela view GerarProvaBean já
	// embaralhada
	@SuppressWarnings("unchecked")
	@Override
	public List<Questao> listarEspecificas(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis,
			String funcao) {
		List<Questao> todasQuestoes = new ArrayList<Questao>();
		List<Questao> questoesFaceis = new ArrayList<Questao>();
		List<Questao> questoesMedias = new ArrayList<Questao>();
		List<Questao> questoesDificeis = new ArrayList<Questao>();
		Query query;

		if (!numeroQuestoesFaceis.equals("0")) {

			query = session
					.createQuery("from Questao where dificuldade='F' and id_funcao='"
							+ funcao + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesFaceis));
			questoesFaceis = query.list();
			for (int i = 0; i < questoesFaceis.size(); i++) {
				todasQuestoes.add(questoesFaceis.get(i));
				System.out.println("facil==" + numeroQuestoesFaceis);
			}
		}
		if (!numeroQuestoesMedias.equals("0")) {
			query = session
					.createQuery("from Questao where dificuldade='M' and id_funcao='"
							+ funcao + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesMedias));
			questoesMedias = query.list();
			for (int i = 0; i < questoesMedias.size(); i++) {
				todasQuestoes.add(questoesMedias.get(i));
				System.out.println("Medio==" + numeroQuestoesMedias);
			}
		}

		if (!numeroQuestoesDificeis.equals("0")) {
			query = session
					.createQuery("from Questao where dificuldade='D' and id_funcao='"
							+ funcao + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesDificeis));
			questoesDificeis = query.list();
			for (int i = 0; i < questoesDificeis.size(); i++) {
				todasQuestoes.add(questoesDificeis.get(i));
				System.out.println("teste==" + numeroQuestoesDificeis);
			}

		}

		return todasQuestoes;
	}

	@SuppressWarnings("unchecked")
	public Questao selecionarQuestaoSubjetiva(String consulta) {
		Query query = session.createQuery(consulta);
		List<Questao> subjetivas = new ArrayList<Questao>();
		subjetivas = query.list();
		Collections.shuffle(subjetivas);

		return subjetivas.get(0);

	}

	@SuppressWarnings("unchecked")
	public List<Questao> listarPorDisciplina(String numeroQuestoesFaceis,
			String numeroQuestoesMedias, String numeroQuestoesDificeis,
			String disciplina) {
		List<Questao> todasQuestoes = new ArrayList<Questao>();
		List<Questao> questoesFaceis = new ArrayList<Questao>();
		List<Questao> questoesMedias = new ArrayList<Questao>();
		List<Questao> questoesDificeis = new ArrayList<Questao>();
		Query query;

		if (!numeroQuestoesFaceis.equals("0")) {

			query = session
					.createQuery("from Questao where dificuldade='F' and disciplina_id='"
							+ disciplina + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesFaceis));
			questoesFaceis = query.list();
			for (int i = 0; i < questoesFaceis.size(); i++) {
				todasQuestoes.add(questoesFaceis.get(i));
				System.out.println("facil==" + numeroQuestoesFaceis);
			}
		}
		if (!numeroQuestoesMedias.equals("0")) {
			query = session
					.createQuery("from Questao where dificuldade='M' and disciplina_id='"
							+ disciplina + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesMedias));
			questoesMedias = query.list();
			for (int i = 0; i < questoesMedias.size(); i++) {
				todasQuestoes.add(questoesMedias.get(i));
				System.out.println("Medio==" + numeroQuestoesMedias);
			}
		}

		if (!numeroQuestoesDificeis.equals("0")) {
			query = session
					.createQuery("from Questao where dificuldade='D' and disciplina_id='"
							+ disciplina + "'");
			query.setMaxResults(Integer.parseInt(numeroQuestoesDificeis));
			questoesDificeis = query.list();
			for (int i = 0; i < questoesDificeis.size(); i++) {
				todasQuestoes.add(questoesDificeis.get(i));
				System.out.println("teste==" + numeroQuestoesDificeis);
			}

		}

		Collections.shuffle(todasQuestoes);

		return todasQuestoes;
	}

	@SuppressWarnings("unchecked")
	public Questao modificarQuestaoDisciplina(String disciplina,
			String dificuldade, int idQuestao) {
		List<Questao> todasQuestoes = new ArrayList<Questao>();
		Query query = session.createQuery("from Questao where disciplina_id='"
				+ disciplina + "' and dificuldade='" + dificuldade+"'");
		todasQuestoes = query.list();
		
		
		Random r;
		int idSorteado, idFixo = idQuestao, iTeste = 0;
		idSorteado = idFixo;
		int contador = 0;
		while (idFixo == idSorteado) {
			r = new Random();
			iTeste = r.nextInt(todasQuestoes.size());
			idSorteado = todasQuestoes.get(iTeste).getId();
			contador++;
			if(contador >= 10){
				System.out.println("======Loop======" + contador);
				break;
			}
		}
		idFixo = idSorteado;
		Questao questaoSorteada = todasQuestoes.get(iTeste);
		return questaoSorteada;
				
	}
}
