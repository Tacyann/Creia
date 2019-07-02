package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceDetalheGuia;
import br.com.dss.servico.ServiceGuia;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext facescontext;
	
	@Inject
	private Flash flash;

	private List<GuiaArgument> guias;
	private List<Double> valoresLiberados;
	private List<Double> valoresGlosa;
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

		flash.put( "clientes", clientes);
		flash.put( "descricao", descricao);
		flash.put( "profissionais", profissionais);
		flash.put( "valorTotal", valorTotal);
		flash.put( "valorGlosa", valorGlosa);
		flash.put( "valorCreia", valorCreia);
		flash.put( "valorProfissional", valorProfissional);
		flash.put("guias", guias);
		return "relatorio?faces-redirect=true";
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
}
