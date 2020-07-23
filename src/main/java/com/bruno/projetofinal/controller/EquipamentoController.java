package com.bruno.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.projetofinal.dao.EquipamentoDAO;
import com.bruno.projetofinal.model.Equipamento;

@CrossOrigin("*")
@RestController
public class EquipamentoController {

	@Autowired
	EquipamentoDAO dao;
	
	@GetMapping("/equipamentos")
	public ArrayList<Equipamento> recuperarTodos() {
		ArrayList<Equipamento> lista = new ArrayList<Equipamento>();
		lista = (ArrayList<Equipamento>) dao.findAll();
		return lista;

	}
	
}
