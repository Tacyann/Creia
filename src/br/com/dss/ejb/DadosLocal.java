package br.com.dss.ejb;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Beneficiario;
import br.com.dss.util.Relatorio;

@Local
public interface DadosLocal {
	@Asynchronous
	public Future<List<Relatorio>> imprimir(String[] clientes, String[] descricao,java.util.Date dtIni, java.util.Date dtFim);
	@Asynchronous
	public Future<List<GuiaArgument>> listar(String[] clientes, String[] descricao,java.util.Date dtIni, java.util.Date dtFim);
	@Asynchronous
	public Future<Double> valorTotal();
	@Asynchronous
	public Future<List<Calendar>> dataEmissao();
	@Asynchronous
	public Future<Double> valorGlosa();
	@Asynchronous
	public Future<Double> valorProfissional();
	@Asynchronous
	public Future<Double> valorCreia();
	@Asynchronous
	public Future<String> nomeClientes();
	@Asynchronous
	public Future<String> nomeProcedimentos();
	@Asynchronous
	public Future<String> periodoRealizacao();
	@Asynchronous
	public Future<List<Beneficiario>> pacientes();
}
