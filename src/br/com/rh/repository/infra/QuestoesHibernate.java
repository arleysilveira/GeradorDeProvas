package br.com.rh.repository.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

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
			String funcao, int numVerdFalsa, int numSubj, int numAlter) {
		List<Questao> todasQuestoes = new CopyOnWriteArrayList<Questao>();
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
		
		Collections.shuffle(todasQuestoes);
		List<Questao> questoesFinais = new ArrayList<Questao>();

		int[] contador = new int[3];
		for(Questao questao : todasQuestoes){
			if(questao.getNumeroAlternativas().equals("1") && contador[0] < numSubj){
				contador[0]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			} else if(questao.getNumeroAlternativas().equals("2") && contador[1] < numVerdFalsa){
				contador[1]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			} else if(questao.getNumeroAlternativas().equals("5") && contador[2] < numAlter){
				contador[2]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			}
		}
		
		for (Questao questao : todasQuestoes){
			if(contador[0] < numSubj){
				contador[0]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=1");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			} else if(contador[1] < numVerdFalsa){
				contador[1]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=2");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			} else if(contador[2] < numAlter){
				contador[2]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=5");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			}
		}
		
		

		return questoesFinais;
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
			String disciplina, int numVerdFalsa, int numSubj, int numAlter) {
		List<Questao> todasQuestoes = new CopyOnWriteArrayList<Questao>();
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
		List<Questao> questoesFinais = new ArrayList<Questao>();

		int[] contador = new int[3];
		for(Questao questao : todasQuestoes){
			if(questao.getNumeroAlternativas().equals("1") && contador[0] < numSubj){
				contador[0]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			} else if(questao.getNumeroAlternativas().equals("2") && contador[1] < numVerdFalsa){
				contador[1]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			} else if(questao.getNumeroAlternativas().equals("5") && contador[2] < numAlter){
				contador[2]++;
				questoesFinais.add(questao);
				todasQuestoes.remove(questao);
			}
		}
		
		for (Questao questao : todasQuestoes){
			if(contador[0] < numSubj){
				contador[0]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=1");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			} else if(contador[1] < numVerdFalsa){
				contador[1]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=2");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			} else if(contador[2] < numAlter){
				contador[2]++;
				query = session.createQuery("from Questao where dificuldade='"+questao.getDificuldade()+"' and numero_alternativas=5");
				Questao resultado = (Questao) query.list().get(0);
				questoesFinais.add(resultado);
				todasQuestoes.remove(questao);
			}
		}
		
		return questoesFinais;
	}

	@SuppressWarnings("unchecked")
	public Questao modificarQuestaoDisciplina(String idDisciplina,
			String dificuldade, int idQuestao) {
		List<Questao> todasQuestoes = new ArrayList<Questao>();
		Query query = session.createQuery("from Questao where disciplina_id='"
				+ idDisciplina + "' and dificuldade='" + dificuldade+"'");
		todasQuestoes = query.list();
		
		
		Random r;
		int idSorteado, idFixo = idQuestao, idTeste = 0;
		idSorteado = idFixo;
		int contador = 0;
		while (idFixo == idSorteado) {
			r = new Random();
			idTeste = r.nextInt(todasQuestoes.size());
			idSorteado = todasQuestoes.get(idTeste).getId();
			if(contador >= 10){
				System.out.println("======Loop======" + contador);
				break;
			}
			contador++;
		}
		idFixo = idSorteado;
		Questao questaoSorteada = todasQuestoes.get(idTeste);
		return questaoSorteada;
				
	}
	
	@SuppressWarnings("unchecked")
	public Questao modificarQuestaoFuncao(String idFuncao,
			String dificuldade, int idQuestao) {
		List<Questao> todasQuestoes = new ArrayList<Questao>();
		Query query = session.createQuery("from Questao where id_funcao='"
				+ idFuncao + "' and dificuldade='" + dificuldade+"'");
		todasQuestoes = query.list();
		
		
		Random r;
		int idSorteado, idFixo = idQuestao, idTeste = 0;
		idSorteado = idFixo;
		int contador = 0;
		while (idFixo == idSorteado) {
			r = new Random();
			idTeste = r.nextInt(todasQuestoes.size());
			idSorteado = todasQuestoes.get(idTeste).getId();
			if(contador >= 10){
				System.out.println("======Loop======" + contador);
				break;
			}
			contador++;
		}
		idFixo = idSorteado;
		Questao questaoSorteada = todasQuestoes.get(idTeste);
		return questaoSorteada;
				
	}
}
