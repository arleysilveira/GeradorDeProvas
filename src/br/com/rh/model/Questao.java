package br.com.rh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Questao implements Serializable{
	private Integer id;
	private String titulo, alternativa1, alternativa2, alternativaCorreta,
	alternativa3, alternativa4, alternativa5, numeroAlternativas, dificuldade;
	//private Prova prova;
	private Funcao funcao;
	private Disciplina disciplina; 

	@Id 
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column
	public String getAlternativa1() {
		return alternativa1;
	}

	public void setAlternativa1(String alternativa1) {
		this.alternativa1 = alternativa1;
	}

	@Column
	public String getAlternativa2() {
		return alternativa2;
	}

	public void setAlternativa2(String alternativa2) {
		this.alternativa2 = alternativa2;
	}

	@Column
	public String getAlternativa3() {
		return alternativa3;
	}

	
	public void setAlternativa3(String alternativa3) {
		this.alternativa3 = alternativa3;
	}

	@Column
	public String getAlternativa4() {
		return alternativa4;
	}

	
	public void setAlternativa4(String alternativa4) {
		this.alternativa4 = alternativa4;
	}

	@Column
	public String getAlternativa5() {
		return alternativa5;
	}

	public void setAlternativa5(String alternativa5) {
		this.alternativa5 = alternativa5;
	}

	@Column(name="numero_alternativas")
	public String getNumeroAlternativas() {
		return numeroAlternativas;
	}

	public void setNumeroAlternativas(String numeroAlternativas) {
		this.numeroAlternativas = numeroAlternativas;
	}
	
	@ManyToOne
	@JoinColumn(name="disciplina_id")
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	@ManyToOne
	@JoinColumn(name="id_funcao")
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
	
/*	@ManyToOne
	@JoinColumn(name="prova_id")
	public Prova getProva() {
		return prova;
	}



	public void setProva(Prova prova) {
		this.prova = prova;
	}*/


	@Column(name="alternativa_correta")
	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alternativa1 == null) ? 0 : alternativa1.hashCode());
		result = prime * result
				+ ((alternativa2 == null) ? 0 : alternativa2.hashCode());
		result = prime * result
				+ ((alternativa3 == null) ? 0 : alternativa3.hashCode());
		result = prime * result
				+ ((alternativa4 == null) ? 0 : alternativa4.hashCode());
		result = prime * result
				+ ((alternativa5 == null) ? 0 : alternativa5.hashCode());
		result = prime
				* result
				+ ((alternativaCorreta == null) ? 0 : alternativaCorreta
						.hashCode());
		result = prime * result
				+ ((dificuldade == null) ? 0 : dificuldade.hashCode());
		result = prime * result
				+ ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((numeroAlternativas == null) ? 0 : numeroAlternativas
						.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Questao other = (Questao) obj;
		if (alternativa1 == null) {
			if (other.alternativa1 != null)
				return false;
		} else if (!alternativa1.equals(other.alternativa1))
			return false;
		if (alternativa2 == null) {
			if (other.alternativa2 != null)
				return false;
		} else if (!alternativa2.equals(other.alternativa2))
			return false;
		if (alternativa3 == null) {
			if (other.alternativa3 != null)
				return false;
		} else if (!alternativa3.equals(other.alternativa3))
			return false;
		if (alternativa4 == null) {
			if (other.alternativa4 != null)
				return false;
		} else if (!alternativa4.equals(other.alternativa4))
			return false;
		if (alternativa5 == null) {
			if (other.alternativa5 != null)
				return false;
		} else if (!alternativa5.equals(other.alternativa5))
			return false;
		if (alternativaCorreta == null) {
			if (other.alternativaCorreta != null)
				return false;
		} else if (!alternativaCorreta.equals(other.alternativaCorreta))
			return false;
		if (dificuldade == null) {
			if (other.dificuldade != null)
				return false;
		} else if (!dificuldade.equals(other.dificuldade))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroAlternativas == null) {
			if (other.numeroAlternativas != null)
				return false;
		} else if (!numeroAlternativas.equals(other.numeroAlternativas))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
}