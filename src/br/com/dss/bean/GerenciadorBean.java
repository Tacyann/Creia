package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceDetalheGuia;
import br.com.dss.servico.ServiceGlosa;
import br.com.dss.servico.ServiceGuia;

@Named("gerenciador")
@SessionScoped
public class GerenciadorBean implements Serializable {

	@Inject
	private FacesContext facescontext;

	private List<Guia> guias;
	private List<DetalheGuia> detalhes;
	private List<Double> valoresLiberados;
	private String[] clientes;
	private String[] descricao;
	private Double valorTotal;
	private Double valorGlosa;
	private Double valorCreia;
	private Double valorProfissional;
	private Date dtInicial;
	private Date dtFinal;

	public void registros() {

		guias = new ArrayList<>();
		valoresLiberados = new ArrayList<>();

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		ServiceDetalheGuia sdg = new ServiceDetalheGuia();

		if(clientes.length == 0) {
			limpar();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Nenhum cliente foi selecionado para a consulta.");
			facescontext.addMessage(null, msg);
		}else {

			java.sql.Date dt1 = new java.sql.Date(getDtInicial().getTime());
			java.sql.Date dt2 = new java.sql.Date(getDtFinal().getTime());

			@SuppressWarnings("unchecked")
			var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, dt1, dt2);

			for(var item : listagemGuia) {
				var guia = new Guia();
				guia.setPrestador(item.getPrestador());
				guia.setOperadora(item.getOperadora());
				guia.setSenha(item.getSenha());
				guia.setBeneficiario(item.getBeneficiario());
				guia.setDataIni(item.getDataIni());
				guia.setSituacaoGuia(item.getSituacaoGuia());

//				var glosa = item.getGlosa();
//				
//				if(glosa != null) {
//					guia.setGlosa(glosa);	
//					for(var g : glosa) {
//						System.out.println(g.getDescricao());
//					}
//				}
				
				guia.setValorInformadoGuia(item.getValorInformadoGuia());
				guia.setValorProcessadoGuia(item.getValorProcessadoGuia());
				guia.setValorLiberadoGuia(item.getValorProcessadoGuia());
				var detalheGuia = (DetalheGuia) servico.Obter(sdg, guia.getPrestador());			
				detalhes.add(detalheGuia);

				//valoresLiberados.add(detalheGuia.getValorLiberado());
				guias.add(guia);
			}

			limpar();
			for(var valor : guias) {
				var liberado = valor.getValorLiberadoGuia();
				valorTotal = valorTotal + liberado;

				if(liberado == 0) {
					valorGlosa = valorGlosa + valor.getValorInformadoGuia();
				}
			}
			valorCreia = valorTotal * 45.5 / 100;
			valorProfissional = valorTotal * 54.5 / 100;
		}
	}

	public void limpar() {
		if(guias.size()>0) {
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
	
	public void listar() {

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		ServiceDetalheGuia sdg = new ServiceDetalheGuia();
		@SuppressWarnings("unchecked")
		var listagemGuia = (List<Guia>) servico.Listar(sg);

		for(var item : listagemGuia) {
			var guia = new Guia();
			guia.setPrestador(item.getPrestador());
			guia.setOperadora(item.getOperadora());
			guia.setSenha(item.getSenha());
			guia.setBeneficiario(item.getBeneficiario());
			guia.setDataIni(item.getDataIni());
			guia.setSituacaoGuia(item.getSituacaoGuia());
			
//			var glosa = item.getGlosa();
//			
//			if(glosa != null) {
//				guia.setGlosa(glosa);	
//				for(var g : glosa) {
//					System.out.println(g.getDescricao());
//				}
//			}
			
			guia.setValorInformadoGuia(item.getValorInformadoGuia());
			guia.setValorProcessadoGuia(item.getValorProcessadoGuia());
			guia.setValorLiberadoGuia(item.getValorProcessadoGuia());
			@SuppressWarnings("unchecked")
			var detalheGuia = (List<DetalheGuia>) servico.Obter(sdg, guia.getPrestador());			
			guia.setDetalheGuia(detalheGuia);

			//valoresLiberados.add(detalheGuia.getValorLiberado());
			guias.add(guia);
		}

		limpar();
		for(var valor : guias) {
			var liberado = valor.getValorLiberadoGuia();
			valorTotal = valorTotal + liberado;
			
			if(liberado == 0) {
				valorGlosa = valorGlosa + valor.getValorInformadoGuia();
			}
		}
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

	public List<Guia> getGuias() {
		return guias;
	}

	public void setGuias(List<Guia> guias) {
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
}
