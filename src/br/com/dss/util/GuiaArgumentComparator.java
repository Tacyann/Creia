package br.com.dss.util;

import java.util.Comparator;

import br.com.dss.argument.GuiaArgument;

public class GuiaArgumentComparator implements Comparator<GuiaArgument>{

	@Override
	public int compare(GuiaArgument o1, GuiaArgument o2) {
//		if(o1.getBeneficiario().getNome() == o2.getBeneficiario().getNome()) {
//			return 0;
//		}else if( o1.getBeneficiario().getNome().compareTo(o2.getBeneficiario().getNome()) > 0 ) {
//			return 1;
//		}
		
		if(o1.getDetalheGuia().getProcedimento().getDescricao() == o2.getDetalheGuia().getProcedimento().getDescricao()) {
			return 0;
		}else if(o1.getDetalheGuia().getProcedimento().getDescricao().compareTo(o2.getDetalheGuia().getProcedimento().getDescricao()) > 0) {
			return 1;
		}
		
		return -1;
	}
}
