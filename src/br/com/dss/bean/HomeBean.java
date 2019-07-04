package br.com.dss.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("home")
@RequestScoped
public class HomeBean implements Serializable{

	public String sobre() {
		return "sobre";
	}
	
	public String cadastrar() {
		return "cadastro";
	}
}
