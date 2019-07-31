package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.ejb.DadosLocal;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Procedimento;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceGuia;
import br.com.dss.util.Relatorio;
import br.com.dss.util.UtilRelatorios;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext context;
	
	@EJB
	private DadosLocal geraDados;

	private Set<Procedimento> listaProced;
	private List<GuiaArgument> guias;
	private List<Double> valoresLiberados;
	private List<Double> valoresGlosa;
	private static List<Relatorio> relatorios; 
	private static String nomeClientes;
	private String[] clientes;
	private String[] descricao;
	private String selectProfissionais;
	private String profissional;
	private Double valorTotal = 0.0;
	private Double valorGlosa = 0.0;
	private Double valorCreia = 0.0;
	private Double valorProfissional = 0.0;
	private Date dtInicial;
	private Date dtFinal;

	public void registros() {

		guias = new ArrayList<>();
		valoresLiberados = new ArrayList<>();
		valoresGlosa = new ArrayList<>();

		var tamProc = descricao.length;
		String textoProc = "";
		if(tamProc == 0) {
			textoProc = " Nunhum procedimento foi selecionado.";
		}
		else if(tamProc == 1) {
			textoProc = " procedimento está selecionado.";
		}else {
			textoProc = " procedimentos estão selecionados.";
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, tamProc + textoProc);
		context.addMessage(null, msg);
		
		var tamC = clientes.length;
		String textoCliente = "";
		if(tamC == 0) {
			textoCliente = " Nenhum cliente foi selecionado.";
		}else if(tamC == 1) {
			textoCliente = " cliente foi selecionado.";
		}else {
			textoCliente = " clientes foram selecionados.";
		}
		FacesMessage msgCliente = new FacesMessage(FacesMessage.SEVERITY_INFO, null, tamC + textoCliente);
		context.addMessage(null, msgCliente);
		
		try {
			guias = geraDados.listar(clientes, descricao, getDtInicial(), getDtFinal()).get();
			valorTotal = geraDados.valorTotal().get();
			valorGlosa = geraDados.valorGlosa().get();
			valorCreia = geraDados.valorCreia().get();
			valorProfissional = geraDados.valorProfissional().get();
			
		} catch (InterruptedException e) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
			context.addMessage(null, msgErro);
		} catch (ExecutionException e) {
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
			context.addMessage(null, msgErro);
		}
		
//		java.sql.Date dt1 = new java.sql.Date(getDtInicial().getTime());
//		java.sql.Date dt2 = new java.sql.Date(getDtFinal().getTime());
//
//		@SuppressWarnings("unchecked")
//		var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, descricao, dt1, dt2);
//		try {
//			for(var item : listagemGuia) {
//				var guia = new GuiaArgument();
//				var detalhe = new DetalheGuia();
//				guia.setPrestador(item.getPrestador());
//				guia.setOperadora(item.getOperadora());
//				var cliente = item.getBeneficiario();
//				if(cliente != null) {
//					guia.setBeneficiario(item.getBeneficiario());					
//				}
//				guia.setDataIni(item.getDataIni());
//
//				detalhe.setDataRealizacao(item.getDtRealizacao());
//				var procedimento = item.getProcedimento();
//				if(procedimento != null) {
//					guia.setProcedimento(procedimento);						
//				}
//
//				var informado = item.getValorInformado();
//				detalhe.setValorInformado(informado);
//				detalhe.setQtdExecutada(item.getQtdExecutada());
//				detalhe.setValorProcessado(item.getValorProcessado());
//				var liberado = item.getValorLiberado();
//				detalhe.setValorLiberado(liberado);
//
//				if(liberado == 0.0) {
//					valoresGlosa.add(informado);
//					detalhe.setValorGlosa(informado);
//				}
//
//				valoresLiberados.add(liberado);
//				guia.setDetalheGuia(detalhe);
//				guias.add(guia);
//			}
//		}catch(NullPointerException e) {
//			System.out.println(e.getCause());
//			System.out.println(e.getClass());
//			System.out.println(e.getMessage());
//			System.out.println(e.getLocalizedMessage());
//			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
//			context.addMessage(null, msgErro);
//		}catch(RuntimeException e) {
//			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Erro: " + e.getMessage());
//			context.addMessage(null, msgErro);
//		}
//
//		valorTotal = 0.0;
//		valorGlosa = 0.0;
//		valorCreia = 0.0;
//		valorProfissional = 0.0;
//
//		for(var total : valoresLiberados) {
//			valorTotal =+ valorTotal + total;
//		}
//		valorCreia = valorTotal * 45.5 / 100;
//
//		for(var glosa : valoresGlosa) {
//			valorGlosa =+ valorGlosa + glosa;				
//		}
//		valorProfissional = valorTotal * 54.5 / 100;
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

	public String sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		limpar();
		return "home?faces-redirect=true";
	}

	public String gerarImpressao() {
		
		var ret = geraDados.imprimir(clientes, descricao, getDtInicial(), getDtFinal());

		try {
			relatorios = ret.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		if(clientes.length > 0) {
			Set<String> nCliente = new HashSet<String>();			
			StringBuilder sb = new StringBuilder();

			for(var c : clientes) {
				nCliente.add(c);
			}
			for(var c : nCliente) {
				sb.append(c).append("\n");
			}
			setNomeClientes(sb.toString());
		}else {
			Service servico = new Service();
			ServiceBeneficiario sbn = new ServiceBeneficiario();

			@SuppressWarnings("unchecked")
			var listagemC = (List<Beneficiario>) servico.Listar(sbn);

			Set<String> nCliente = new HashSet<String>();			
			StringBuilder sb = new StringBuilder();

			for(var c : listagemC) {
				nCliente.add(c.getNome());
			}
			for(var c : nCliente) {
				sb.append(c).append("\n");
			}
			setNomeClientes(sb.toString());
		}
		
		imprimeRelatorio();

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void imprimeRelatorio() {
		HashMap parametros = new HashMap();
		parametros.put("NOME_ESPECIALISTA", selectProfissionais);
		parametros.put("NOME_CLIENTE", nomeClientes);
		parametros.put("TOTAL_PROFISSIONAL", valorProfissional);
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

	public List<Double> getValoresLiberados() {
		return valoresLiberados;
	}

	public void setValoresLiberados(List<Double> valoresLiberados) {
		this.valoresLiberados = valoresLiberados;
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

	public List<Double> getValoresGlosa() {
		return valoresGlosa;
	}

	public void setValoresGlosa(List<Double> valoresGlosa) {
		this.valoresGlosa = valoresGlosa;
	}
	public List<Relatorio> getRelatorios() {
		return relatorios;
	}
	public Set<Procedimento> getListaProced() {
		return listaProced;
	}

	public void setListaProced(Set<Procedimento> listaProced) {
		this.listaProced = listaProced;
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
		return nomeClientes;
	}
	public static void setNomeClientes(String nomeClientes) {
		GerenciadorBean.nomeClientes = nomeClientes;
	}
}
