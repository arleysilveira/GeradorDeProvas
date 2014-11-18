import java.util.List;

import org.hibernate.Session;

import br.com.rh.model.Questao;
import br.com.rh.util.HibernateUtil;


public class TesteHibernate {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Session session = HibernateUtil.getsessionSession();
		
		List<Questao> provas = session.createCriteria(Questao.class).list();
		
		for(Questao p : provas){
			System.out.println(p.getTitulo()+"-");
		}
		
		session.clear();
	}
}
