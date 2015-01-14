import java.util.List;

import org.hibernate.Session;

import br.com.rh.model.Questao;
import br.com.rh.util.HibernateUtil;


public class TesteHibernate {
	public static void main(String[] args) {
		Session session = HibernateUtil.getsessionSession();
		
		@SuppressWarnings("unchecked")
		List<Questao> questoes = session.createCriteria(Questao.class).list();
		
		for(Questao p : questoes){
			System.out.println(p.getTitulo()+"-");
		}
		
		session.clear();
	}
}
