package br.com.rh.util;

import java.io.Serializable;

import org.hibernate.Session;

import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.infra.DisciplinasHibernate;

@SuppressWarnings("serial")
public class Repositorios implements Serializable{

	public Disciplinas getDisciplinas(){
		return new DisciplinasHibernate(this.getSession());
	}

	private Session getSession(){
		return (Session) FacesUtil.getRequestAttribute("session");
	}
}
