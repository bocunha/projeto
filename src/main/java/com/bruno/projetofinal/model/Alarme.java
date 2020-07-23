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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "itmn_alarme")
public class Alarme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alarme")
	private int id;

	@Column(name = "nome", length = 100)
	private String nome;

	@Column(name = "descricao", length = 200)
	private String descricao;

	@OneToMany(mappedBy = "alarme", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("alarme")
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING)
	private List<Evento> eventosAlarme;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Evento> getEventosAlarme() {
		return eventosAlarme;
	}

	public void setEventosAlarme(List<Evento> eventosAlarme) {
		this.eventosAlarme = eventosAlarme;
	}

}
