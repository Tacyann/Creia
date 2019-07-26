package br.com.dss.servico;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.dss.argument.GuiaArgument;
import br.com.dss.modelo.Guia;

public class TesteServico {
	public static void main(String[] args) {
		List<Double> valoresLiberados = new ArrayList<>();
		List<Double> valoresGlosa = new ArrayList<>();
		var lista = teste();

		Double totalLib = 0.0;
		for(var item : lista) {
			valoresLiberados.add(item.getValorLiberado());
			System.out.println(item.getValorLiberado());
			totalLib += totalLib + item.getValorLiberado();
		}
		Double totalGlo = 0.0;
		
		System.out.println(totalLib);
	}

	public static List<GuiaArgument> teste(){
		List<GuiaArgument> guias = new ArrayList<>();

		Service servico = new Service();
		ServiceGuia sg = new ServiceGuia();

		var dt1 = Date.valueOf("2018-12-01");
		var dt2 = Date.valueOf("2019-07-08");

		@SuppressWarnings("unchecked")
		var listagemGuia = (Collection<Guia>) servico.Obter(sg, dt1, dt2);
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
