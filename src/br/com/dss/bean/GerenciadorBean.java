package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.ClienteArgument;
import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.modelo.Procedimento;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceDetalheGuia;
import br.com.dss.servico.ServiceGuia;
import br.com.dss.util.ClienteArgumentComparator;
import br.com.dss.util.GuiaArgumentComparator;
import br.com.dss.util.ProcedimentoComparator;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext facescontext;

	private Set<ClienteArgument> listaCliente; 
	private Set<Procedimento> listaProced;
	private List<GuiaArgument> guias;
	private List<Double> valoresLiberados;
	private List<Double> valoresGlosa;
	private List<ClienteArgument> clienteArgs;
	private ClienteArgument clienteArg;
	private String[] clientes;
	private String[] descricao;
	private String[] profissionais;
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
			facescontext.addMessage(null, msg);
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
				facescontext.addMessage(null, msg);
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

				//				var glosa = item.getGlosa();
				//				
				//				if(glosa != null) {
				//					guia.setGlosa(glosa);	
				//					for(var g : glosa) {
				//						System.out.println(g.getDescricao());
				//					}
				//				}

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
				facescontext.addMessage(null, msg);
			}else if(descricao.length == 0) {
				limpar();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum Procedimento foi selecionado para a consulta.");
				facescontext.addMessage(null, msg);
			}else if(clientes.length == 0 && descricao.length == 0) {
				limpar();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum Procedimento foi selecionado para a consulta.");
				facescontext.addMessage(null, msg);
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
			facescontext.addMessage(null, msg);
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
		limpar();
		return "home?faces-redirect=true";
	}

	public String gerarImpressao() {
		double vlrInformado = 0.0;
		double vlrLiberado = 0.0;
		double vlrGlosa = 0.0;
		double vlrProcessado = 0.0;

		if(guias == null && guias.size()==0) {
			limpar();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum cliente foi selecionado para a consulta.");
			facescontext.addMessage(null, msg);
		}

		clienteArgs = new ArrayList<ClienteArgument>();
		guias.sort(new GuiaArgumentComparator());
		listaCliente = new TreeSet<ClienteArgument>(new ClienteArgumentComparator());
		listaProced = new TreeSet<Procedimento>(new ProcedimentoComparator());
		for(int i = 0; i < descricao.length; i++) {
			
			for(var guia : guias) {
				ClienteArgument c = new ClienteArgument();
				var procedimento = guia.getDetalheGuia().getProcedimento().getDescricao();
				listaProced.add(guia.getDetalheGuia().getProcedimento());
				
				if(Integer.parseInt(descricao[i]) == guia.getDetalheGuia().getProcedimento().getProcedimento()) {
					var nome = guia.getBeneficiario().getNome();
					
					vlrInformado = guia.getDetalheGuia().getValorInformado();
					vlrLiberado = guia.getDetalheGuia().getValorLiberado();
					vlrGlosa = guia.getDetalheGuia().getValorGlosa();
					vlrProcessado = guia.getDetalheGuia().getValorProcessado();
					
					c.setBeneficiario(nome);
					c.setProcedimento(procedimento);
					c.setValorInformado(vlrInformado);
					c.setValorLiberado(vlrLiberado);
					c.setValorGlosa(vlrGlosa);
					c.setValorProcessado(vlrProcessado);
					listaCliente.add(c);
				}				
			}
		}
		
		
		for(var guia : listaCliente) {
			ClienteArgument c = new ClienteArgument();
			var nome = guia.getBeneficiario();
			var procedimento = guia.getProcedimento();
			
			vlrInformado = guia.getValorInformado();
			vlrLiberado = guia.getValorLiberado();
			vlrGlosa = guia.getValorGlosa();
			vlrProcessado = guia.getValorProcessado();
			
			c.setBeneficiario(nome);
			c.setProcedimento(procedimento);
			c.setValorInformado(vlrInformado);
			c.setValorLiberado(vlrLiberado);
			c.setValorGlosa(vlrGlosa);
			c.setValorProcessado(vlrProcessado);
			clienteArgs.add(c);
		}
		
//		for(var n : nomes) {
//			ClienteArgument c = new ClienteArgument();
//			vlrInformado = 0.0;
//			vlrLiberado = 0.0;
//			vlrGlosa = 0.0;
//			vlrProcessado = 0.0;
//	
//			for(var p : nomeProcedimentos) {
//				for(var guia : guias) {
//					vlrInformado += guia.getDetalheGuia().getValorInformado();
//					vlrLiberado += guia.getDetalheGuia().getValorLiberado();
//					vlrGlosa += guia.getDetalheGuia().getValorGlosa();
//					vlrProcessado += guia.getDetalheGuia().getValorProcessado();
//				}
//				c.setProcedimento(p);
//			}
//			c.setBeneficiario(n);
//			c.setValorInformado(vlrInformado);
//			c.setValorLiberado(vlrLiberado);
//			c.setValorGlosa(vlrGlosa);
//			c.setValorProcessado(vlrProcessado);
//			clienteArgs.add(c);
//		}
		

//		for(var guia : guias) {
//			vlrInformado += vlrInformado + guia.getDetalheGuia().getValorInformado();
//			vlrLiberado += vlrLiberado + guia.getDetalheGuia().getValorLiberado();
//			vlrGlosa += vlrGlosa + guia.getDetalheGuia().getValorGlosa();
//			vlrProcessado += vlrProcessado + guia.getDetalheGuia().getValorProcessado();
//			
//			ClienteArgument c = new ClienteArgument();
//			c.setBeneficiario(nome);
//			c.setProcedimento(procedimento);
//			c.setValorInformado(vlrInformado);
//			c.setValorLiberado(vlrLiberado);
//			c.setValorGlosa(vlrGlosa);
//			c.setValorProcessado(vlrProcessado);
//			listaCliente.add(c);
//		}
//		for(var lista : listaCliente) {
//			ClienteArgument c = new ClienteArgument();
//			c.setBeneficiario(lista.getBeneficiario());
//			c.setProcedimento(lista.getProcedimento());
//			c.setValorInformado(lista.getValorInformado());
//			c.setValorLiberado(lista.getValorLiberado());
//			c.setValorGlosa(lista.getValorGlosa());
//			c.setValorProcessado(lista.getValorProcessado());
//			clienteArgs.add(c);
//		}

		return "relatorio";
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

			//			var glosa = item.getGlosa();
			//			
			//			if(glosa != null) {
			//				guia.setGlosa(glosa);	
			//				for(var g : glosa) {
			//					System.out.println(g.getDescricao());
			//				}
			//			}

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
		return facescontext;
	}

	public void setFacescontext(FacesContext facescontext) {
		this.facescontext = facescontext;
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

	public String[] getProfissionais() {
		return profissionais;
	}

	public void setPrifissionais(String[] profissionais) {
		this.profissionais = profissionais;
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
	public ClienteArgument getClienteArg() {
		return clienteArg;
	}
	public void setClienteArg(ClienteArgument clienteArg) {
		this.clienteArg = clienteArg;
	}
	public List<ClienteArgument> getClienteArgs() {
		return clienteArgs;
	}
	public void setClienteArgs(List<ClienteArgument> clienteArgs) {
		this.clienteArgs = clienteArgs;
	}

	public Set<Procedimento> getListaProced() {
		return listaProced;
	}

	public void setListaProced(Set<Procedimento> listaProced) {
		this.listaProced = listaProced;
	}
}
