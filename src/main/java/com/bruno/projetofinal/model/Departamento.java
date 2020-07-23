package com.bruno.projetofinal.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // significa que eh um valor gerado pelo banco e que eh
														// autoincremente
	@Column(name = "id")
	private int id;

	@Column(name = "nomedpt", length = 100)
	private String nomedpt;

	@Column(name = "unidade", length = 10)
	private String unidade;

	@JsonIgnoreProperties("depto") //EH O NOME DA MINHA CLASSE QUE AMARRA AS TABELAS NO USUARIO.JAVA
	@OneToMany(mappedBy="depto", cascade=CascadeType.ALL)
	private List<Usuario> usarUsuario;
	
	public List<Usuario> getUsarUsuario() {
		return usarUsuario;
	}

	public void setUsarUsuario(List<Usuario> usarUsuario) {
		this.usarUsuario = usarUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomedpt() {
		return nomedpt;
	}

	public void setNomedpt(String nomedpt) {
		this.nomedpt = nomedpt;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


}