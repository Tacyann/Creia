package br.com.dss.util;

import java.util.Comparator;

public class RelatorioComparator implements Comparator<Relatorio>{

	@Override
	public int compare(Relatorio o1, Relatorio o2) {
		if(o1.getNomeProcedimento() == o2.getNomeProcedimento()) {
			return -1;
		}else if( o1.getNomeProcedimento().compareTo(o2.getNomeProcedimento()) > 0 ) {
			return 1;
		}
		return -1;
	}
}
