package br.com.dss.servico;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Guia;
import br.com.dss.util.Relatorio;

public class TesteServico {
	public static void main(String[] args) {
		//var guias = teste();
		var relato = gerarImpressao();
	}

	public static List<GuiaArgument> gerarImpressao() {
		List<GuiaArgument> guias = new ArrayList<>();
		String[] clientes = {};
		String[] codProced = {};
		
		var dt1 = Date.valueOf("2018-12-01");
		var dt2 = Date.valueOf("2019-07-08");
		
		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();
		
		@SuppressWarnings("unchecked")
		var listagem = (List<Relatorio>) servico.Somar(sg, clientes, codProced, dt1, dt2);
		
		for(var item : listagem) {
			System.out.println(item.getNomeProcedimento() + "Qtd: " + item.getQuantidade());
		}
		return guias;
	}

	public static List<GuiaArgument> teste(){
		List<GuiaArgument> guias = new ArrayList<>();
		String[] clientes = {};
		String[] codProced = {};

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();

		var dt1 = Date.valueOf("2018-12-01");
		var dt2 = Date.valueOf("2019-07-08");

		@SuppressWarnings("unchecked")
		var listagemGuia = (List<Guia>) servico.Obter(sg, clientes, codProced, dt1, dt2);
		try {
			for(var item : listagemGuia) {
				var guia = new GuiaArgument();
				guia.setPrestador(item.getPrestador());
				guia.setOperadora(item.getOperadora());
				var cliente = item.getBeneficiario();
				if(cliente != null) {
					guia.setBeneficiario(item.getBeneficiario());					
				}
				guia.setDataIni(item.getDataIni());
				guia.setCodProced(item.getCodProced());
				guia.setValorInformado(item.getValorInformado());
				guia.setQtdExecutada(item.getQtdExecutada());
				guia.setValorProcessado(item.getValorProcessado());
				guia.setValorLiberado(item.getValorLiberado());
				guias.add(guia);
			}
		}catch(NullPointerException e) {
			System.out.println(e.getCause());
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		
		return guias;
	}
}
