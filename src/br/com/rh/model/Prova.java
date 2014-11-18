package br.com.rh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Prova implements Serializable {
	private String cargo;
	private Integer id;
	private Date dataAplicacao, dataCriacao;
	private Questao questao;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public Date getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Questao getQuestao() {
		return questao;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result
				+ ((dataAplicacao == null) ? 0 : dataAplicacao.hashCode());
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + id;
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prova other = (Prova) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (dataAplicacao == null) {
			if (other.dataAplicacao != null)
				return false;
		} else if (!dataAplicacao.equals(other.dataAplicacao))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id != other.id)
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		return true;
	}
	
	
	
}
