package com.bruno.projetofinal.dto;

public class consultaPeriodo {

	private String inicio;
	private String fim;
	
	public consultaPeriodo(String inicio, String fim) {
		super();
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
}
