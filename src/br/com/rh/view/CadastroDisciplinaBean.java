package br.com.rh.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rh.model.Disciplina;
import br.com.rh.repository.Disciplinas;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroDisciplinaBean implements Serializable{
	private Disciplina disciplina;
	private Repositorios repositorios = new Repositorios();

	public CadastroDisciplinaBean() {
		this.disciplina = new Disciplina();
	}

	public void cadastrar() {
		System.out.println(System.getProperty("user.dir"));
		if (this.disciplina.getNome() == "") {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Campo Obrigatório",
					"Favor informar o nome da disciplina");
		} else if (this.disciplina.getNome().length() < 5) {
			FacesUtil.mensagemDetalhada("frm:nome", FacesMessage.SEVERITY_ERROR, "Nome incompleto",
					"A disciplina deve possuir no mínimo 5 letras");
		} else {
			if(this.disciplina.getId() != null){
				FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Alteração concluída com sucesso");
			} else {
				FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro concluído com sucesso");
			}
			Disciplinas disciplinas = this.repositorios.getDisciplinas();
			disciplinas.guardar(this.disciplina);

			this.disciplina = new Disciplina();
		}
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
		if(this.disciplina == null){
			this.disciplina = new Disciplina();
		}
	}

	
	
}
