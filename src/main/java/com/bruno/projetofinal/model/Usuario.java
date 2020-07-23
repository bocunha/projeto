package com.bruno.projetofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // significa que eh um valor gerado pelo banco e que eh
														// autoincremente
	@Column(name = "id")
	private int id;

	@Column(name = "name", length = 100)
	private String nome;

	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Column(name = "racf", length = 7)
	private String racf;

	@Column(name = "senha", length = 50)
	private String senha;

	@Column(name = "linkFoto", length = 200)
	private String linkFoto;

	@JsonIgnoreProperties("usarUsuario") // eh o nome do meu metodo de array de usuarios NO DEPARTAMENTO.JAVA
	@ManyToOne
	private Departamento depto;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRacf() {
		return racf;
	}

	public void setRacf(String racf) {
		this.racf = racf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}

	public Departamento getDepto() {
		return depto;
	}

	public void setDepto(Departamento depto) {
		this.depto = depto;
	}

}
