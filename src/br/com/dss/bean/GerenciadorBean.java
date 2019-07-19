package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Procedimento;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceDetalheGuia;
import br.com.dss.servico.ServiceGuia;
import br.com.dss.servico.ServiceProcedimento;
import br.com.dss.util.Relatorio;
import br.com.dss.util.UtilRelatorios;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext context;

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

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		ServiceDetalheGuia sdg = new ServiceDetalheGuia();

		if(clientes.length == 0) {
			limpar();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum cliente foi selecionado para a consulta.");
			context.addMessage(null, msg);
		}else {
			if(descricao.length > 0) {
				var tam = descricao.length;
				String texto = "";
				if(tam == 1) {
					texto = " procedimento está selecionado.";
				}else {
					texto = " procedimentos estão selecionados.";
				}
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, descricao.length + texto);
				context.addMessage(null, msg);
			}

			java.sql.Date dt1 = new java.sql.Date(getDtInicial().getTime());
			java.sql.Date dt2 = new java.sql.Date(getDtFinal().getTime());

			@SuppressWarnings("unchecked")
			var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, descricao, dt1, dt2);

			for(var item : listagemGuia) {
				var guia = new GuiaArgument();
				guia.setPrestador(item.getPrestador());
				guia.setOperadora(item.getOperadora());
				var cliente = item.getBeneficiario();
				if(cliente != null) {
					guia.setBeneficiario(item.getBeneficiario());					
				}
				guia.setDataIni(item.getDataIni());

				var detalheGuia = (DetalheGuia) servico.Obter(sdg, guia.getPrestador());

				guia.setDetalheGuia(detalheGuia);
				valoresLiberados.add(detalheGuia.getValorLiberado());
				valoresGlosa.add(detalheGuia.getValorGlosa());
				guias.add(guia);
			}

			valorTotal = 0.0;
			valorGlosa = 0.0;
			valorCreia = 0.0;
			valorProfissional = 0.0;

			for(var total : valoresLiberados) {
				valorTotal =+ valorTotal + total;
			}

			valorCreia = valorTotal * 45.5 / 100;

			for(var glosa : valoresGlosa) {
				valorGlosa =+ valorGlosa + glosa;				
			}

			valorProfissional = valorTotal * 54.5 / 100;
		}
	}

	public void filtroProcedimento() {

		if(guias != null && guias.size()>0) {
			guias.clear();
			valoresLiberados.clear();
			valoresGlosa.clear();

			Service servico = new Service();
			ServiceGuia sg = new ServiceGuia();
			ServiceDetalheGuia sdg = new ServiceDetalheGuia();

			if(clientes.length == 0) {
				limpar();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum cliente foi selecionado para a consulta.");
				context.addMessage(null, msg);
			}else if(descricao.length == 0) {
				limpar();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum Procedimento foi selecionado para a consulta.");
				context.addMessage(null, msg);
			}else if(clientes.length == 0 && descricao.length == 0) {
				limpar();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum Procedimento foi selecionado para a consulta.");
				context.addMessage(null, msg);
			}else {
				java.sql.Date dt1 = new java.sql.Date(getDtInicial().getTime());
				java.sql.Date dt2 = new java.sql.Date(getDtFinal().getTime());

				@SuppressWarnings("unchecked")
				var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, descricao, dt1, dt2);

				for(var item : listagemGuia) {
					var guia = new GuiaArgument();
					guia.setPrestador(item.getPrestador());
					guia.setOperadora(item.getOperadora());
					var cliente = item.getBeneficiario();
					if(cliente != null) {
						guia.setBeneficiario(item.getBeneficiario());					
					}
					guia.setDataIni(item.getDataIni());

					var detalheGuia = (DetalheGuia) servico.Obter(sdg, guia.getPrestador());

					guia.setDetalheGuia(detalheGuia);
					valoresLiberados.add(detalheGuia.getValorLiberado());
					valoresGlosa.add(detalheGuia.getValorGlosa());
					guias.add(guia);
				}

				valorTotal = 0.0;
				valorGlosa = 0.0;
				valorCreia = 0.0;
				valorProfissional = 0.0;

				for(var total : valoresLiberados) {
					valorTotal =+ valorTotal + total;
				}

				valorCreia = valorTotal * 45.5 / 100;

				for(var glosa : valoresGlosa) {
					valorGlosa =+ valorGlosa + glosa;				
				}

				valorProfissional = valorTotal * 54.5 / 100;
			}
		}else {
			limpar();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum cliente foi selecionado para a consulta.");
			context.addMessage(null, msg);
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

	public String sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		//limpar();
		return "home?faces-redirect=true";
	}

	public String gerarImpressao() {
		
		relatorios = new ArrayList<>();
		
		if(descricao.length > 0) {
			for(var proced : descricao) {
				Relatorio r = new Relatorio();
				var nomeP = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).map(s ->{return s.getDetalheGuia().getProcedimento().getDescricao();}).toArray();
				var qtd = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).mapToInt(p -> p.getDetalheGuia().getQtdExecutada()).sum();
				var vlrInf = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).mapToDouble(p -> p.getDetalheGuia().getValorInformado()).sum();
				var vlrGl = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).mapToDouble(p -> p.getDetalheGuia().getValorGlosa()).sum();
				var vlrProc = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).mapToDouble(p -> p.getDetalheGuia().getValorProcessado()).sum();
				var vlrLib = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == Integer.parseInt(proced)).mapToDouble(p -> p.getDetalheGuia().getValorLiberado()).sum();
				
				var n = "";
				for(var item : nomeP) {
					n = item.toString();
				}
				
				r.setNomeProcedimento(n);
				r.setQuantidade(qtd);
				r.setValorInformado(vlrInf * 54.5 / 100);
				r.setValorGlosa(vlrGl * 54.5 / 100);
				r.setValorProcessado(vlrProc * 54.5 / 100);
				r.setValorLiberado(vlrLib * 54.5 / 100);
				relatorios.add(r);
			}
		}else {
			ServiceProcedimento sp = new ServiceProcedimento();
			Service servico = new Service();
			
			@SuppressWarnings("unchecked")
			var listagem = (List<Procedimento>) servico.Listar(sp);
			
			for(var item : listagem) {
				Relatorio r = new Relatorio();
				var qtd = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == item.getProcedimento()).mapToInt(p -> p.getDetalheGuia().getQtdExecutada()).sum();
				var vlrInf = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == item.getProcedimento()).mapToDouble(p -> p.getDetalheGuia().getValorInformado()).sum();
				var vlrGl = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == item.getProcedimento()).mapToDouble(p -> p.getDetalheGuia().getValorGlosa()).sum();
				var vlrProc = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == item.getProcedimento()).mapToDouble(p -> p.getDetalheGuia().getValorProcessado()).sum();
				var vlrLib = guias.stream().filter(p -> p.getDetalheGuia().getProcedimento().getProcedimento() == item.getProcedimento()).mapToDouble(p -> p.getDetalheGuia().getValorLiberado()).sum();
				r.setNomeProcedimento(item.getDescricao());
				r.setQuantidade(qtd);
				r.setValorInformado(vlrInf * 54.5 / 100);
				r.setValorGlosa(vlrGl * 54.5 / 100);
				r.setValorProcessado(vlrProc * 54.5 / 100);
				r.setValorLiberado(vlrLib * 54.5 / 100);
				relatorios.add(r);
			}			
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
		}
		
		return "relatorio";
	}
	
	public static List<Relatorio> Relatorio() {
		return relatorios;
	}
	
	public void imprimeRelatorio() {
		HashMap parametros = new HashMap();
		parametros.put("NOME_ESPECIALISTA", selectProfissionais);
		parametros.put("NOME_CLIENTE", nomeClientes);
		parametros.put("TOTAL_PROFISSIONAL", valorProfissional);
		UtilRelatorios.imprimeRelatorio("especialistas", parametros, relatorios);
		System.out.println(nomeClientes);
		/**
		 * Foi incuido instrução para fechar a sessão.
		 */
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public void listar() {

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		ServiceDetalheGuia sdg = new ServiceDetalheGuia();
		@SuppressWarnings("unchecked")
		var listagemGuia = (List<Guia>) servico.Listar(sg);

		for(var item : listagemGuia) {
			var guia = new GuiaArgument();
			guia.setPrestador(item.getPrestador());
			guia.setOperadora(item.getOperadora());
			guia.setBeneficiario(item.getBeneficiario());
			guia.setDataIni(item.getDataIni());

			var detalheGuia = (DetalheGuia) servico.Obter(sdg, guia.getPrestador());			
			guia.setDetalheGuia(detalheGuia);

			guias.add(guia);
		}

		limpar();
	}

	public List<String> nomeBeneficiario(){

		List<String> nomes = new ArrayList<>();
		Service servico = new Service();
		ServiceBeneficiario sb = new ServiceBeneficiario();

		@SuppressWarnings("unchecked")
		var listagem = (List<Beneficiario>) servico.Listar(sb);

		for(var item : listagem) {
			nomes.add(item.getNome());
		}
		return nomes;
	}

	public FacesContext getFacescontext() {
		return context;
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
