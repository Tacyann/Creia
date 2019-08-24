package br.com.dss.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.ejb.DadosLocal;
import br.com.dss.util.Relatorio;
import br.com.dss.util.UtilRelatorios;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext context;
	
	@EJB
	private DadosLocal geraDados;

	private List<GuiaArgument> guias;
	private List<Calendar> datas;
	private static List<Relatorio> relatorios; 
	private static String nomeProcedimento;
	private String[] clientes;
	private String[] descricao;
	private String selectProfissionais;
	private String profissional;
	private Double valorTotal = 0.0;
	private Double valorGlosa = 0.0;
	private Double valorCreia = 0.0;
	private Double valorProfissional = 0.0;
	private static String periodo;
	private Date dtEmissao;
	private Date dtInicial;
	private Date dtFinal;

	public void registros() {
		var tamProc = descricao.length;
		String textoProc = "";
		if(tamProc == 0) {
			textoProc = " Nenhum procedimento selecionado.";
		}
		else if(tamProc == 1) {
			textoProc = " procedimento selecionado.";
		}else {
			textoProc = " procedimentos selecionados.";
		}
		FacesMessage msgProc = new FacesMessage(FacesMessage.SEVERITY_INFO, null, tamProc + textoProc);
		context.addMessage(null, msgProc);
		
		var tamC = clientes.length;
		String textoCliente = "";
		if(tamC == 0) {
			textoCliente = " Nenhum cliente selecionado.";
		}else if(tamC == 1) {
			textoCliente = " cliente selecionado.";
		}else {
			textoCliente = " clientes selecionados.";
		}
		FacesMessage msgCliente = new FacesMessage(FacesMessage.SEVERITY_INFO, null, tamC + textoCliente);
		context.addMessage(null, msgCliente);
		
		try {
			guias = geraDados.listar(clientes, descricao, getDtInicial(), getDtFinal()).get();
			valorTotal = geraDados.valorTotal().get();
			valorGlosa = geraDados.valorGlosa().get();
			valorCreia = geraDados.valorCreia().get();
			valorProfissional = geraDados.valorProfissional().get();
			
			if(guias.size() == 0) {
				StringBuilder sb = new StringBuilder("Não há dados para os parâmetros passados.");
				if(clientes.length > 0) {
					sb.append("\nCliente(s): " + clientes.length);
				}
				if(descricao.length > 0) {
					sb.append("\nProcedimento(s): " + descricao.length);
				}
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, sb.toString());
				context.addMessage(null, msg);
			}
			
		} catch (InterruptedException e) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
			context.addMessage(null, msgErro);
		} catch (ExecutionException e) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
			context.addMessage(null, msgErro);
		}
	}

	public void limpar() {
		if(guias != null && guias.size()>0) {
			guias.clear();
			valorTotal = 0.0;
			valorGlosa = 0.0;
			valorCreia = 0.0;
			valorProfissional = 0.0;
		}
	}
	
	public void dtEmissao() {
		try {
			datas = geraDados.dataEmissao().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public String sair() {
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		limpar();
		return "home?faces-redirect=true";
	}

	public void gerarImpressao() {
		try {
			relatorios = geraDados.imprimir(clientes, descricao, getDtInicial(), getDtFinal()).get();
			nomeProcedimento = geraDados.nomeProcedimentos().get();
			periodo = geraDados.periodoRealizacao().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		imprimeRelatorio();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void imprimeRelatorio() {
		HashMap parametros = new HashMap();
		parametros.put("NOME_ESPECIALISTA", selectProfissionais);
//		parametros.put("NOME_CLIENTE", nomeClientes);
		parametros.put("NOME_PROCEDIMENTO", nomeProcedimento);
		parametros.put("TOTAL_PROFISSIONAL", valorProfissional);
		parametros.put("PERIODO", periodo);
		UtilRelatorios.imprimeRelatorio("especialistas", parametros, relatorios);
	}
	
	public static List<Relatorio> Relatorio() {
		return relatorios;
	}

	public void setFacescontext(FacesContext facescontext) {
		this.context = facescontext;
	}

	public List<GuiaArgument> getGuias() {
		return guias;
	}

	public void setGuias(List<GuiaArgument> guias) {
		this.guias = guias;
	}

	public List<Calendar> getDatas() {
		return datas;
	}

	public void setDatas(List<Calendar> datas) {
		this.datas = datas;
	}

	public String[] getClientes() {
		return clientes;
	}

	public void setClientes(String[] clientes) {
		this.clientes = clientes;
	}

	public String[] getDescricao() {
		return descricao;
	}

	public void setDescricao(String[] descricao) {
		this.descricao = descricao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Double getValorCreia() {
		return valorCreia;
	}

	public Double getValorProfissional() {
		return valorProfissional;
	}

	public Double getValorGlosa() {
		return valorGlosa;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public String getProfissional() {
		return profissional;
	}

	public void setProfissional(String profissional) {
		this.profissional = profissional;
	}

	public String getSelectProfissionais() {
		return selectProfissionais;
	}

	public void setSelectProfissionais(String selectProfissionais) {
		this.selectProfissionais = selectProfissionais;
	}
	
	public static String getNomeClientes() {
		return nomeProcedimento;
	}
	
	public static void setNomeClientes(String nomeClientes) {
		GerenciadorBean.nomeProcedimento = nomeClientes;
	}
	
	public String getPeriodo() {
		return periodo;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
}
