package br.com.rh.repository.infra;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.rh.model.PaginaInformacoes;
import br.com.rh.repository.Paginas;

public class PaginasHibernate implements Paginas{

	private Session session;
	
	public PaginasHibernate(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PaginaInformacoes> listarTodas() {
		return session.createCriteria(PaginaInformacoes.class).addOrder(Order.asc("id")).list();
	}

	@Override
	public PaginaInformacoes guardar(PaginaInformacoes pagina) {
		this.session.merge(pagina);
		return null;
	}

	@Override
	public void excluir(PaginaInformacoes pagina) {
		this.session.delete(pagina);
		
	}

	@Override
	public PaginaInformacoes porCodigo(Integer codigo) {
		return (PaginaInformacoes) session.get(PaginaInformacoes.class, codigo);
	}

}
