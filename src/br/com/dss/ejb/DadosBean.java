package br.com.dss.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.modelo.DetalheGuia;
import br.com.dss.modelo.Guia;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceBeneficiario;
import br.com.dss.servico.ServiceGuia;
import br.com.dss.util.Relatorio;

/**
 * Session Bean implementation class Dados
 */
@Stateless
public class DadosBean implements DadosLocal {
	
	List<Double> valoresGlosa = new ArrayList<>();
	List<Double> valoresLiberados = new ArrayList<>();
	List<String> clientesSelecionados = new ArrayList<>();
	List<String> procedimentosSelecionados = new ArrayList<>();
	Set<String> periodos = new HashSet<>();
	Double valorTotal;
	Set<Calendar> dtEmissao = new HashSet<>();
	boolean existeUsuario;

	@Override
	public Future<List<Relatorio>> imprimir(String[] clientes, String[] descricao, java.util.Date dtIni, java.util.Date dtFim) {
		List<Relatorio> relatorios = new ArrayList<>();
		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		java.sql.Date dt1 = new java.sql.Date(dtIni.getTime());
		java.sql.Date dt2 = new java.sql.Date(dtFim.getTime());
		periodos.clear();
		procedimentosSelecionados.clear();

		@SuppressWarnings("unchecked")
		var listagem = (List<Relatorio>) servico.Somar(sg, clientes, descricao, dt1, dt2);
		for(var item : listagem) {
			var r = new Relatorio();
			var nomeProcedimento = item.getNomeProcedimento();
			var nomePaciente = item.getNomePaciente();
			var qtd = item.getQuantidade();
			var informado = item.getValorInformado();
			var glosa = item.getValorGlosa();
			var processado = item.getValorProcessado();
			var liberado = item.getValorLiberado();
			var periodo = item.getPeriodo();
			periodos.add(periodo);

			procedimentosSelecionados.add(nomeProcedimento.toUpperCase());
			r.setNomeProcedimento(nomeProcedimento);
			r.setNomePaciente(nomePaciente);
			r.setQuantidade(qtd);
			r.setValorInformado(informado * 54.5 / 100);
			r.setValorGlosa(glosa * 54.5 / 100);
			r.setValorProcessado(processado * 54.5 / 100);
			r.setValorLiberado(liberado * 54.5 / 100);
			r.setPeriodo(periodo);
			relatorios.add(r);
		}

		return new AsyncResult<List<Relatorio>>(relatorios);
	}

	@Override
	public Future<List<GuiaArgument>> listar(String[] clientes, String[] descricao, Date dtIni, Date dtFim) {
		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		java.sql.Date dt1 = new java.sql.Date(dtIni.getTime());
		java.sql.Date dt2 = new java.sql.Date(dtFim.getTime());
		List<GuiaArgument> guias = new ArrayList<>();
		valoresLiberados.clear();
		valoresGlosa.clear();
		clientesSelecionados.clear();
		dtEmissao.clear();
		
		@SuppressWarnings("unchecked")
		var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, descricao, dt1, dt2);
		try {
			for(var item : listagemGuia) {
				var guia = new GuiaArgument();
				var detalhe = new DetalheGuia();
				dtEmissao.add(item.getDataEmissao());
				guia.setDataEmissao(item.getDataEmissao());
				guia.setPrestador(item.getPrestador());
				guia.setOperadora(item.getOperadora());
				var cliente = item.getBeneficiario();
				if(cliente != null) {
					guia.setBeneficiario(item.getBeneficiario());	
					clientesSelecionados.add(item.getBeneficiario().getNome());
				}
				guia.setDataIni(item.getDataIni());

				detalhe.setDataRealizacao(item.getDtRealizacao());
				var procedimento = item.getProcedimento();
				if(procedimento != null) {
					guia.setProcedimento(procedimento);						
				}

				var informado = item.getValorInformado();
				detalhe.setValorInformado(informado);
				detalhe.setQtdExecutada(item.getQtdExecutada());
				detalhe.setValorProcessado(item.getValorProcessado());
				var liberado = item.getValorLiberado();
				detalhe.setValorLiberado(liberado);

				if(liberado == 0.0) {
					valoresGlosa.add(informado);
					detalhe.setValorGlosa(informado);
				}

				valoresLiberados.add(liberado);
				guia.setDetalheGuia(detalhe);
				guias.add(guia);
			}
		}catch(NullPointerException e) {
			System.out.println(e.getCause());
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());

		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}

		return new AsyncResult<List<GuiaArgument>>(guias);
	}

	@Override
	public Future<Double> valorTotal() {
		valorTotal = 0.0;
		for(var total : valoresLiberados) {
			valorTotal =+ valorTotal + total;
		}
		return new AsyncResult<Double>(valorTotal);
	}
	
	@Override
	public Future<Double> valorGlosa() {
		var valorGlosa = 0.0;
		for(var glosa : valoresGlosa) {
			valorGlosa =+ valorGlosa + glosa;				
		}
		return new AsyncResult<Double>(valorGlosa);
	}
	
	@Override
	public Future<Double> valorProfissional() {
		var valorProfissional = valorTotal * 54.5 / 100;
		return new AsyncResult<Double>(valorProfissional);
	}
	
	@Override
	public Future<Double> valorCreia() {
		var valorCreia = valorTotal * 45.5 / 100;
		return new AsyncResult<Double>(valorCreia);
	}
	
	@Override
	public Future<List<Calendar>> dataEmissao() {
		List<Calendar> dataEmissao = new ArrayList<>();
		for(var dt : dtEmissao) {
			dataEmissao.add(dt);
		}
		return new AsyncResult<List<Calendar>>(dataEmissao);
	}
	
	@Override
	public Future<List<Beneficiario>> pacientes() {
		Service servico = new Service();
		ServiceBeneficiario sb = new ServiceBeneficiario();

		@SuppressWarnings("unchecked")
		var listagem = (List<Beneficiario>) servico.Listar(sb);
		return new AsyncResult<List<Beneficiario>>(listagem);
	}
	
	@Override
	public Future<String> nomeProcedimentos() {
		var procedimentos = getProcedimentosSelecionados();
		Set<String> nProcedimentos = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		var count = 0;
		for(var item : procedimentos) {
			nProcedimentos.add(item);
		}
		for(var item : nProcedimentos) {
			sb.append(item);
			count++;
			if(nProcedimentos.size() != count) {
				sb.append("\n");
			}
		}
		return new AsyncResult<String>(sb.toString());
	}
	
	@Override
	public Future<String> nomeClientes() {
		var clientes = getClientesSelecionados();
		Set<String> nCliente = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		var count = 0;
		for(var item : clientes) {
			nCliente.add(item);
		}
		for(var item : nCliente) {
			sb.append(item);
			count++;
			if(nCliente.size() != count) {
				sb.append("\n");
			}
		}
		return new AsyncResult<String>(sb.toString());
	}
	
	@Override
	public Future<String> periodoRealizacao() {
		var dt = getPeriodos();
		StringBuilder sb = new StringBuilder();
		var count = 0;
		for(var item : dt) {
			sb.append(item);
			count++;
			if(dt.size() != count) {
				sb.append(" ");
			}
		}
		return new AsyncResult<String>(sb.toString());
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public Set<Calendar> getDtEmissao() {
		return dtEmissao;
	}

	public List<Double> getValoresGlosa() {
		return valoresGlosa;
	}

	public List<Double> getValoresLiberados() {
		return valoresLiberados;
	}
	
	public List<String> getClientesSelecionados() {
		return clientesSelecionados;
	}
	
	public List<String> getProcedimentosSelecionados() {
		return procedimentosSelecionados;
	}

	public Set<String> getPeriodos() {
		return periodos;
	}
	
	public boolean isExisteUsuario() {
		return existeUsuario;
	}
}