package com.bruno.projetofinal.dto;

public class VolumeEquipamentos {
	private int idEquipamento;
	private long qtde;
	private String hostname;
	
	
	public  VolumeEquipamentos(int idEquipamento, long qtde) {
		super();
		this.idEquipamento = idEquipamento;
		this.qtde = qtde;
	}
	public  VolumeEquipamentos(int idEquipamento, String hostname,long qtde) {
		super();
		this.idEquipamento = idEquipamento;
		this.qtde = qtde;
		this.hostname=hostname;
	}
	public int getIdEquipamento() {
		return idEquipamento;
	}


	public void setIdEquipamento(int idEquipamento) {
		this.idEquipamento = idEquipamento;
	}


	public long getQtde() {
		return qtde;
	}


	public void setQtde(long qtde) {
		this.qtde = qtde;
	}


	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
}