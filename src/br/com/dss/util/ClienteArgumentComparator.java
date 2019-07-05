package br.com.dss.util;

import java.util.Comparator;

import br.com.dss.argument.ClienteArgument;

public class ClienteArgumentComparator implements Comparator<ClienteArgument>{

	@Override
	public int compare(ClienteArgument o1, ClienteArgument o2) {
		if(o1.getBeneficiario() == o2.getBeneficiario()) {
			return 0;
		}else if( o1.getBeneficiario().compareTo(o2.getBeneficiario()) > 0 ) {
			return 1;
		}
		return -1;
	}
}
