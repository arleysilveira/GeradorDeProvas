package br.com.rh.util;

import java.io.Serializable;

import org.hibernate.Session;

import br.com.rh.repository.Cargos;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Questoes;
import br.com.rh.repository.infra.CargosHibernate;
import br.com.rh.repository.infra.DisciplinasHibernate;
import br.com.rh.repository.infra.FuncoesHibernate;
import br.com.rh.repository.infra.QuestoesHibernate;

@SuppressWarnings("serial")
public class Repositorios implements Serializable{

	public Disciplinas getDisciplinas(){
		return new DisciplinasHibernate(this.getSession());
	}
	
	public Funcoes getFuncoes(){
		return new FuncoesHibernate(this.getSession());
	}
	
	public Cargos getCargos(){
		return new CargosHibernate(this.getSession());
	}
	
	public Questoes getQuestoes(){
		return new QuestoesHibernate(this.getSession());
	}
	
	private Session getSession(){
		return (Session) FacesUtil.getRequestAttribute("session");
	}
}
