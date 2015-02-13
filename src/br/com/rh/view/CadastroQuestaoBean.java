package br.com.rh.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.FileItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Funcao;
import br.com.rh.model.Questao;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Questoes;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class CadastroQuestaoBean implements Serializable{
	private Questao questao;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	private Repositorios repositorios = new Repositorios();
	private UploadedFile file;
	
	public CadastroQuestaoBean(){
		this.questao = new Questao();
	}
	
	@PostConstruct
	public void init(){
		Disciplinas disciplinas = this.repositorios.getDisciplinas();
		this.disciplinas = disciplinas.listarTodas();
		
		Funcoes funcoes = this.repositorios.getFuncoes();
		this.funcoes = funcoes.listarTodas();
		
	}

	public void cadastrar() throws IOException{
		Questoes questoes = this.repositorios.getQuestoes();
		questoes.guardar(this.questao);
		
		this.questao = new Questao();
	}
	
	public void testeFile(FileUploadEvent event) throws IOException{
	    byte[] conteudo = event.getFile().getContents();  
	    String localPath = System.getProperty("user.dir");  
	    
	    String caminho = (localPath + "/git/GeradorDeProvas/WebContent/imagens/" + event.getFile().getFileName());  
	    System.out.println(caminho);  
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.addMessage(null, new FacesMessage("Sucesso"));
	    FileOutputStream fos = new FileOutputStream(caminho);  
	    fos.write(conteudo);  
	    fos.close();  
	}
	
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
		if(this.questao == null){
			this.questao = new Questao();
		}
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Repositorios getRepositorios() {
		return repositorios;
	}

	public void setRepositorios(Repositorios repositorios) {
		this.repositorios = repositorios;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	
	
}
