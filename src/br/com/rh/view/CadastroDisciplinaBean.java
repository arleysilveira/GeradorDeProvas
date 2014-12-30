package br.com.rh.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;

import br.com.rh.model.Disciplina;
import br.com.rh.util.FacesUtil;

@ManagedBean
public class CadastroDisciplinaBean {
	private Disciplina disciplina;

	public CadastroDisciplinaBean() {
		this.disciplina = new Disciplina();
	}

	public void cadastrar() {
		if (this.disciplina.getNome() == "") {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Campo Obrigatório",
					"Favor informar o nome da disciplina");
		} else if (this.disciplina.getNome().length() < 5) {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Nome incompleto",
					"A disciplina deve possuir no mínimo 5 letras");
		} else {
			
			
			Session session = (Session) FacesUtil.getRequestAttribute("session");

			session.merge(this.disciplina);

			this.disciplina = new Disciplina();
			FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro concluído");
		}
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
