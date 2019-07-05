package br.com.dss.util;

import java.util.Comparator;

import br.com.dss.modelo.Procedimento;

public class ProcedimentoComparator implements Comparator<Procedimento>{

	@Override
	public int compare(Procedimento o1, Procedimento o2) {
		if(o1.getDescricao() == o2.getDescricao()) {
			return 0;
		}else if( o1.getDescricao().compareTo(o2.getDescricao()) > 0 ) {
			return 1;
		}
		return -1;
	}
}
