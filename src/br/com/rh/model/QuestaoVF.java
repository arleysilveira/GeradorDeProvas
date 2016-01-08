package br.com.rh.model;

@SuppressWarnings("serial")
public class QuestaoVF extends Questao{
	
	private Integer Id;
	private String alternativa1;
	private String alternativa2;
	private String alternativa_correta;
	private Questao questao;
	
	
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}
	
	public String getAlternativa1() {
		return alternativa1;
	}
	
	public void setAlternativa1(String alternativa1) {
		this.alternativa1 = alternativa1;
	}
	
	public String getAlternativa2() {
		return alternativa2;
	}
	
	public void setAlternativa2(String alternativa2) {
		this.alternativa2 = alternativa2;
	}
	
	public String getAlternativa_correta() {
		return alternativa_correta;
	}
	
	public void setAlternativa_correta(String alternativa_correta) {
		this.alternativa_correta = alternativa_correta;
	}
	
	public Questao getQuestao() {
		return questao;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	
	
}
