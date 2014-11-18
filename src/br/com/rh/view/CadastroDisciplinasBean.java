package br.com.rh.view;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.rh.model.Disciplinas;
import br.com.rh.util.HibernateUtil;

@ManagedBean
public class CadastroDisciplinasBean {
	private Disciplinas disciplina;

	public CadastroDisciplinasBean() {
		this.disciplina = new Disciplinas();
	}

	public void cadastrar() {
		if (this.disciplina.getNome() == "") {
			this.adicionaMensagem("frm:nome", FacesMessage.SEVERITY_ERROR, "Campo Obrigatório",
					"Favor informar o nome da disciplina");
		} else if (this.disciplina.getNome().length() < 5) {
			this.adicionaMensagem("frm:nome", FacesMessage.SEVERITY_ERROR, "Nome incompleto",
					"A disciplina deve possuir no mínimo 5 letras");
		} else {
			Session session = HibernateUtil.getsessionSession();
			Transaction trx = session.beginTransaction();

			session.merge(this.disciplina);

			trx.commit();
			session.close();
			this.disciplina = new Disciplinas();
			this.adicionaMensagem(null, FacesMessage.SEVERITY_INFO, "Cadastro concluído",
					"Cadastro efetuado com sucesso");
		}
	}

	private void adicionaMensagem(String clientId, Severity severity,
			String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, summary, detail);
		context.addMessage(clientId, message);
	}

	public Disciplinas getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplinas disciplina) {
		this.disciplina = disciplina;
	}

}
