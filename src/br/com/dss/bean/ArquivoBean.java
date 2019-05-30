package br.com.dss.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.dao.ArquivoDao;

@Named("arquivo")
@RequestScoped
public class ArquivoBean {
	
	public Object getArquivo() throws Exception {
		ArquivoDao c = new ArquivoDao();
		
		return c.ArquivoXml();
	}
}
