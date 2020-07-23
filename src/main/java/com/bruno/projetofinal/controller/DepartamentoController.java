package com.bruno.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.projetofinal.dao.DepartamentoDAO;
import com.bruno.projetofinal.dao.UsuarioDAO;
import com.bruno.projetofinal.model.Departamento;
import com.bruno.projetofinal.model.Usuario;

@CrossOrigin("*")
@RestController
public class DepartamentoController {

	@Autowired
	private DepartamentoDAO dao;
	
	@Autowired
	private UsuarioDAO udao;

	@GetMapping("/departamentos")
	public ArrayList<Departamento> recuperarTodos() {
		ArrayList<Departamento> lista = new ArrayList<Departamento>();
		lista = (ArrayList<Departamento>) dao.findAll();
		return lista;
	}

	@GetMapping("/departamentos/*/{idu}")
	public Usuario reucuperaDepartamentoeUsuario(@PathVariable int idu) {
		System.out.println("teste" + idu);
		Usuario user = udao.findById(idu).orElse(null);
		return user;
	}

	@GetMapping("/departamentos/{id}")
	public Departamento recuperarPorId(@PathVariable int id) {
		Departamento depto = dao.findById(id).orElse(null);
		return depto;
	}

}
