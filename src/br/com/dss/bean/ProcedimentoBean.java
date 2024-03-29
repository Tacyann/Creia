package br.com.dss.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.dss.modelo.Procedimento;
import br.com.dss.servico.Service;
import br.com.dss.servico.ServiceProcedimento;

@Named("procedimento")
@RequestScoped
public class ProcedimentoBean implements Serializable{

	private List<Procedimento> procedimentos = new ArrayList<>();
	private String[] descricao;
	
	@PostConstruct
	public void inicializar() {
		ServiceProcedimento sp = new ServiceProcedimento();
		Service servico = new Service();

		@SuppressWarnings("unchecked")
		var listagem = (List<Procedimento>) servico.Listar(sp);
		
		for(var item : listagem) {
			procedimentos.add(item);
		}
	}
	
	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}
	public String[] getDescricao() {
		return descricao;
	}
	public void setDescricao(String[] descricao) {
		this.descricao = descricao;
	}
}
