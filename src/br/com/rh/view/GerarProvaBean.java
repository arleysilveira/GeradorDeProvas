package br.com.rh.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.lowagie.text.DocumentException;

import br.com.rh.model.Disciplina;
import br.com.rh.model.Funcao;
import br.com.rh.model.PaginaInformacoes;
import br.com.rh.model.Prova;
import br.com.rh.model.Questao;
import br.com.rh.repository.Disciplinas;
import br.com.rh.repository.Funcoes;
import br.com.rh.repository.Paginas;
import br.com.rh.repository.Questoes;
import br.com.rh.util.FacesUtil;
import br.com.rh.util.Html2Pdf;
import br.com.rh.util.MergePdf;
import br.com.rh.util.Repositorios;

/**
 * @author arley
 *
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class GerarProvaBean implements Serializable {
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	private List<PaginaInformacoes> paginas = new ArrayList<PaginaInformacoes>();

	private String[] questoesFaceis = new String[3];
	private String[] questoesMedias = new String[3];
	private String[] questoesDificeis = new String[3];
	private int[] verdFalso = new int[3];
	private int[] alternativas = new int[3];
	private int[] subjetivas = new int[3];

	private Repositorios repositorios = new Repositorios();

	private List<Questao> questoesSelecionadas = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas2 = new ArrayList<Questao>();
	private List<Questao> questoesSelecionadas3 = new ArrayList<Questao>();

	private Prova prova, prova2, prova3;
	private Questao questaoModificada, questaoSubjetiva;
	private Boolean subjetiva = false;
	private PaginaInformacoes paginaSelecionada;

	public GerarProvaBean() {
		this.prova = new Prova();
		this.prova2 = new Prova();
		this.prova3 = new Prova();
	}

	@PostConstruct
	public void init() {
		Disciplinas disciplinas = repositorios.getDisciplinas();
		this.disciplinas = disciplinas.listarTodas();

		Funcoes funcoes = repositorios.getFuncoes();
		this.funcoes = funcoes.listarTodas();

		Paginas paginas = repositorios.getPaginas();
		this.paginas = paginas.listarTodas();

	}

	// Pega as questões do banco de acordo com os parâmetros passados pelo
	// usuário
	public String listarQuestoes() {
		if(verificarNumeroQuestoes()){
			return null;
		}
		Questoes questoes = repositorios.getQuestoes();
		this.questoesSelecionadas = questoes.listarPorDisciplina(questoesFaceis[0], questoesMedias[0],
				questoesDificeis[0], prova.getDisciplinaSelecionada().getId().toString(), verdFalso[0], subjetivas[0],
				alternativas[0]);
		this.questoesSelecionadas2 = questoes.listarPorDisciplina(questoesFaceis[1], questoesMedias[1],
				questoesDificeis[1], prova2.getDisciplinaSelecionada().getId().toString(), verdFalso[1], subjetivas[1],
				alternativas[1]);
		this.questoesSelecionadas3 = questoes.listarEspecificas(questoesFaceis[2], questoesMedias[2],
				questoesDificeis[2], prova3.getFuncaoSelecionada().getId().toString(), verdFalso[2], subjetivas[2],
				alternativas[2]);
		
		return "questao/ListaDeQuestoes.xhtml?faces-redirect=true";
	}

	// Função para modificar a Questão
	public void modificarQuestao() {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas.indexOf(this.questaoModificada);

		this.questoesSelecionadas.set(posicaoQuestao,
				questoes.modificarQuestaoDisciplina(this.getQuestaoModificada().getDisciplina().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));
	}

	public void modificarQuestao2() {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas2.indexOf(this.questaoModificada);

		this.questoesSelecionadas2.set(posicaoQuestao,
				questoes.modificarQuestaoDisciplina(this.getQuestaoModificada().getDisciplina().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));

	}

	public void modificarQuestao3() throws SQLException {
		Questoes questoes = repositorios.getQuestoes();
		int posicaoQuestao = this.questoesSelecionadas3.indexOf(this.questaoModificada);

		this.questoesSelecionadas3.set(posicaoQuestao,
				questoes.modificarQuestaoFuncao(this.getQuestaoModificada().getFuncao().getId().toString(),
						this.questaoModificada.getDificuldade(), this.questaoModificada.getId()));
	}

	public boolean verificarNumeroQuestoes() {
		int q, r;
		for(int i = 0; i < 3; i++){
			if(this.questoesFaceis[i].equals("")){
				this.questoesFaceis[i] = "0" ;
			} 
			
			if(this.questoesMedias[i].equals("")){
				this.questoesMedias[i] = "0" ;
			} 
			
			if(this.questoesDificeis[i].equals("")){
				this.questoesDificeis[i] = "0" ;
			} 
			
			q = Integer.parseInt(this.questoesFaceis[i]) + Integer.parseInt(this.questoesMedias[i])
					+ Integer.parseInt(this.questoesDificeis[i]);
			r = this.verdFalso[i] + this.subjetivas[i] + this.alternativas[i];
			
			if (q != r) {
				FacesUtil.adicionaMensagem(FacesMessage.SEVERITY_ERROR,
						"A soma do número de questões fácil, Médio e Díficil deve ser igual a soma do número de questões V/F, Subj. e Alternat");
				return true;
			}
		}

		return false;
		
	}

	String caminho;

	public void gerarProva() throws SQLException, DocumentException, IOException, SAXException {
		/*
		 * JasperReport report; try { report = JasperCompileManager
		 * .compileReport(
		 * "/home/arley/git/GeradorDeProvas/WebContent/relatorios/report1.jrxml"
		 * ); JasperPrint print = JasperFillManager.fillReport(report, null, new
		 * JRBeanCollectionDataSource(this.questoesSelecionadas));
		 * JasperExportManager.exportReportToPdfFile(print,
		 * "/home/arley/git/GeradorDeProvas/WebContent/relatorios/RelatorioClientes.pdf"
		 * ); } catch (JRException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// Transforma todo o conteúdo html em String
		URL url = new URL("http://localhost:8080/GeradorPerguntas/prova.html");
		InputStream in = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String questoes = "";
		String paginaInicial = "";
		String linha;

		while (!(linha = reader.readLine()).equals("fim")) {
			paginaInicial += linha;
		}
		while ((linha = reader.readLine()) != null) {
			questoes += linha;
		}

		paginaInicial = paginaInicial.replaceAll("pagina", this.paginaSelecionada.getTexto());

		// Libera os recursos.
		reader.close();
		in.close();

		// Substitui os parâmetros da página html pelas questões
		int i = 1;
		for (Questao q : questoesSelecionadas) {
			questoes = questoes.replaceFirst("Q" + i, i + ") " + q.getTitulo());
			if (q.getNumeroAlternativas().equals("5")) {
				questoes = questoes.replaceFirst("alt" + i,
						"<br>" + "a) " + q.getAlternativa1() + "<br>" + "b) " + q.getAlternativa2() + "<br>" + "c) "
								+ q.getAlternativa3() + "<br>" + "d) " + q.getAlternativa4() + "<br>" + "e) "
								+ q.getAlternativa5() + "<br>");
			} else if (q.getNumeroAlternativas().equals("2")) {
				questoes = questoes.replaceFirst("alt" + i, "<br>Verdadeira" + "<br>" + "Falsa<br>");
			} else {
				questoes = questoes.replaceFirst("alt" + i,
						"_____________________________________________________________________________________<br>"
								+ "_____________________________________________________________________________________");
			}

			i++;
		}

		for (Questao q : questoesSelecionadas2) {
			questoes = questoes.replaceFirst("Q" + i, i + ") " + q.getTitulo());
			if (q.getNumeroAlternativas().equals("5")) {
				questoes = questoes.replaceFirst("alt" + i,
						"<br>" + q.getAlternativa1() + "<br>" + q.getAlternativa2() + "<br>" + q.getAlternativa3()
								+ "<br>" + q.getAlternativa4() + "<br>" + q.getAlternativa5() + "<br>");
			} else if (q.getNumeroAlternativas().equals("2")) {
				questoes = questoes.replaceFirst("alt" + i, "<br>Verdadeira" + "<br>" + "Falsa<br>");
			} else {
				questoes = questoes.replaceFirst("alt" + i,
						"_____________________________________________________________________________________<br>"
								+ "_____________________________________________________________________________________");
			}

			i++;
		}

		for (Questao q : questoesSelecionadas3) {
			questoes = questoes.replaceFirst("Q" + i, i + ") " + q.getTitulo());
			if (q.getNumeroAlternativas().equals("5")) {
				questoes = questoes.replaceFirst("alt" + i,
						"<br>" + q.getAlternativa1() + "<br>" + q.getAlternativa2() + "<br>" + q.getAlternativa3()
								+ "<br>" + q.getAlternativa4() + "<br>" + q.getAlternativa5() + "<br>");
			} else if (q.getNumeroAlternativas().equals("2")) {
				questoes = questoes.replaceFirst("alt" + i, "<br>Verdadeira" + "<br>" + "Falsa<br>");
			} else {
				questoes = questoes.replaceFirst("alt" + i,
						"_____________________________________________________________________________________<br>"
								+ "_____________________________________________________________________________________");
			}

			i++;
		}

		// Elimina os parâmetros que sobraram no arquivo Html
		while (i < 50) {
			questoes = questoes.replaceFirst("Q" + i, "");
			questoes = questoes.replaceFirst("alt" + i, "");
			i++;
		}

		OutputStream os = new FileOutputStream("/home/arley/perguntas.pdf");
		OutputStream os1 = new FileOutputStream("/home/arley/paginaInicial.pdf");
		Html2Pdf.convert(questoes, os);
		Html2Pdf.convert(paginaInicial, os1);

		List<InputStream> pdfs = new ArrayList<InputStream>();
		pdfs.add(new FileInputStream("/home/arley/paginaInicial.pdf"));
		pdfs.add(new FileInputStream("/home/arley/perguntas.pdf"));
		OutputStream output = new FileOutputStream("/home/arley/prova.pdf");
		MergePdf.concatPDFs(pdfs, output, true);
		InputStream arquivoPdf = new FileInputStream("/home/arley/prova.pdf");
		byte[] b = IOUtils.toByteArray(arquivoPdf);

		/*
		 * Descomentar quando for para produção Tomcat HttpSession session =
		 * (HttpSession)
		 * FacesContext.getCurrentInstance().getExternalContext().getSession(
		 * false); caminho =
		 * session.getServletContext().getRealPath("/"+"provas/" ); pdfs.add(new
		 * FileInputStream(this.caminho + "paginaInicial.pdf")); pdfs.add(new
		 * FileInputStream(this.caminho + "perguntas.pdf")); OutputStream output
		 * = new FileOutputStream(this.caminho + "prova.pdf");
		 * MergePdf.concatPDFs(pdfs, output, true); InputStream arquivoPdf = new
		 * FileInputStream(this.caminho + "prova.pdf"); byte[] b =
		 * IOUtils.toByteArray(arquivoPdf);
		 */

		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		res.setContentType("application/pdf");

		// Código abaixo gerar o relatório e disponibiliza para o cliente baixar
		// ou visualizar
		res.setHeader("Content-disposition", "attachment;filename=prova.pdf");

		// Código abaixo gerar o relatório e disponibiliza diretamente na página
		// res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
		res.getOutputStream().write(b);
		res.getCharacterEncoding();
		FacesContext.getCurrentInstance().responseComplete();

		os.close();

	}

	// Getters and Setters
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Prova getProva2() {
		return prova2;
	}

	public void setProva2(Prova prova2) {
		this.prova2 = prova2;
	}

	public Prova getProva3() {
		return prova3;
	}

	public void setProva3(Prova prova3) {
		this.prova3 = prova3;
	}

	public List<Questao> getQuestoesSelecionadas() {
		return questoesSelecionadas;
	}

	public void setQuestoesSelecionadas(List<Questao> questoesSelecionadas) {
		this.questoesSelecionadas = questoesSelecionadas;
	}

	public List<Questao> getQuestoesSelecionadas2() {
		return questoesSelecionadas2;
	}

	public void setQuestoesSelecionadas2(List<Questao> questoesSelecionadas2) {
		this.questoesSelecionadas2 = questoesSelecionadas2;
	}

	public List<Questao> getQuestoesSelecionadas3() {
		return questoesSelecionadas3;
	}

	public void setQuestoesSelecionadas3(List<Questao> questoesSelecionadas3) {
		this.questoesSelecionadas3 = questoesSelecionadas3;
	}

	public Questao getQuestaoModificada() {
		return questaoModificada;
	}

	public void setQuestaoModificada(Questao questaoModificada) {
		this.questaoModificada = questaoModificada;
	}

	public Questao getQuestaoSubjetiva() {
		return questaoSubjetiva;
	}

	public void setQuestaoSubjetiva(Questao questaoSubjetiva) {
		this.questaoSubjetiva = questaoSubjetiva;
	}

	public Boolean getSubjetiva() {
		return subjetiva;
	}

	public void setSubjetiva(Boolean subjetiva) {
		this.subjetiva = subjetiva;
	}

	public String[] getQuestoesFaceis() {
		return questoesFaceis;
	}

	public void setQuestoesFaceis(String[] questoesFaceis) {
		this.questoesFaceis = questoesFaceis;
	}

	public String[] getQuestoesMedias() {
		return questoesMedias;
	}

	public void setQuestoesMedias(String[] questoesMedias) {
		this.questoesMedias = questoesMedias;
	}

	public String[] getQuestoesDificeis() {
		return questoesDificeis;
	}

	public void setQuestoesDificeis(String[] questoesDifíceis) {
		this.questoesDificeis = questoesDifíceis;
	}

	public int[] getVerdFalso() {
		return verdFalso;
	}

	public void setVerdFalso(int[] verdFalso) {
		this.verdFalso = verdFalso;
	}

	public int[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(int[] alternativas) {
		this.alternativas = alternativas;
	}

	public int[] getSubjetivas() {
		return subjetivas;
	}

	public void setSubjetivas(int[] subjetivas) {
		this.subjetivas = subjetivas;
	}

	public List<PaginaInformacoes> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PaginaInformacoes> paginas) {
		this.paginas = paginas;
	}

	public PaginaInformacoes getPaginaSelecionada() {
		return paginaSelecionada;
	}

	public void setPaginaSelecionada(PaginaInformacoes paginaSelecionada) {
		this.paginaSelecionada = paginaSelecionada;
	}

}
