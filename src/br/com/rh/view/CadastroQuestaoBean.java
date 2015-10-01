package br.com.rh.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
//import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Funcao;
import br.com.rh.model.Questao;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Questoes;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
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

	public void cadastrar() {
		verificaTipoQuestao();
		if(this.questao.getId() != null){
			FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Questão alterada com sucesso");
		} else {
			FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Questão cadastrada com sucesso");
		}
		Questoes questoes = this.repositorios.getQuestoes();
		questoes.guardar(this.questao);
		
		this.questao = new Questao();
		
	}
	
	String nomeArquivo;
	String arquivo;
	String caminho;
	
	public void testeFile(FileUploadEvent event) throws IOException{
		byte[] conteudo = event.getFile().getContents();  
	    String localPath = System.getProperty("user.dir");  
	    
	    this.nomeArquivo = event.getFile().getFileName();
	    
	    /*Descomentar quando for para produção Tomcat
	    HttpSession session = (HttpSession)    
	    	    FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    caminho = session.getServletContext().getRealPath("/"+"imagens-prova/" + event.getFile().getFileName());*/
	   
	    caminho = (localPath + "/git/GeradorDeProvas/WebContent/imagens-prova/" + this.nomeArquivo);
	    OutputStream fos = new FileOutputStream(caminho);  
	    fos.write(conteudo);  
	    fos.close();  
	}
	
	public void inserirImagem(){
		this.arquivo = "<img src=\"http://localhost:8080/GeradorPerguntas/imagens-prova/" + this.nomeArquivo + "\">";
		String titulo = questao.getTitulo() + arquivo;
		Charset.forName("UTF-8").encode(titulo);
		this.questao.setTitulo(titulo); 
	}
	
	private void verificaTipoQuestao(){
		if(this.questao.getNumeroAlternativas().equals("2")){
			this.questao.setAlternativa1("1"); //Representa Verdadeira
			this.questao.setAlternativa2("0"); //Representa o Falsa
			this.questao.setAlternativa3(null);
			this.questao.setAlternativa4(null);
			this.questao.setAlternativa5(null);
		}
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

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
}
