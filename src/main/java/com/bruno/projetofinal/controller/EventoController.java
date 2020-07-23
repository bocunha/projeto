package com.bruno.projetofinal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.projetofinal.dao.EventoDAO;
import com.bruno.projetofinal.dto.VolumeAlarmes;
import com.bruno.projetofinal.dto.consultaPeriodo;
import com.bruno.projetofinal.model.Evento;

@CrossOrigin("*")
@RestController
public class EventoController {

	@Autowired
	EventoDAO dao;
	
	@GetMapping("/eventos")
	public ArrayList<Evento> recuperarTodosCrescente() {
		ArrayList<Evento> lista;
		lista = (ArrayList<Evento>) dao.findByOrderByDataAsc();
		return lista;
	}

	@GetMapping("/eventos/decresc")
	public ArrayList<Evento> recuperarTodosDecrescente() {
		ArrayList<Evento> lista;
		lista = (ArrayList<Evento>) dao.findByOrderByDataDesc();
		return lista;
	}

	@GetMapping("/eventos/{id}")
	public Evento recuperarPorId(@PathVariable int id) {
		Evento evento = dao.findById(id).orElse(null);
		return evento;
	}

	@GetMapping("/eventos/alarmes")
	public ArrayList<VolumeAlarmes> recuperarTodosNome() {
		ArrayList<VolumeAlarmes> top = dao.getAllWithName();
		return top;
	}
	

//	@GetMapping("/eventos/top")
//	public Evento findByOrderByDataTop() {
//		Evento top = dao.findByOrderByData();
//		return top;
//	}
	

	@GetMapping("/eventos/alarmes/top")
	public ArrayList<VolumeAlarmes> recuperarTopNome() {
		ArrayList<VolumeAlarmes> top = dao.getTopWithName();
		return top;
	}

	@PostMapping("/eventos/alarmes/periodo")
	public ArrayList<VolumeAlarmes> recuperarJaneiro(@RequestBody consultaPeriodo periodo){
		try {
			Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getInicio());
			Date fim = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getFim());
			return dao.getAllWithNameWithPeriod(inicio, fim);
		} catch (Exception ex) {
			return null;
		}
	}

	

	@GetMapping("/eventos/alarmes/manual")
	public ArrayList<VolumeAlarmes> recuperarManual() {
		ArrayList<Evento> lista;
		ArrayList<VolumeAlarmes> ret;

		lista = (ArrayList<Evento>) dao.findByOrderByDataDesc();
		ret = new ArrayList<VolumeAlarmes>();
		long count_1 = 0, count_2 = 0, count_3 = 0, count_4 = 0, count_5 = 0;
		String nome_1 = "", nome_2 = "", nome_3 = "", nome_4 = "", nome_5 = "";
		for (Evento e : lista) {
			switch (e.getAlarme().getId()) {
			case 1: count_1++; break;
			case 2: count_2++; break;
			case 3: count_3++; break;
			case 4: count_4++; break;
			case 5:	count_5++; break;
			}
			nome_1 = e.getAlarme().getNome();
			nome_2 = e.getAlarme().getNome();
			nome_3 = e.getAlarme().getNome();
			nome_4 = e.getAlarme().getNome();
			nome_5 = e.getAlarme().getNome();

		}
		ret.add(new VolumeAlarmes(1, nome_1, count_1));
		ret.add(new VolumeAlarmes(2, nome_2, count_2));
		ret.add(new VolumeAlarmes(3, nome_3, count_3));
		ret.add(new VolumeAlarmes(4, nome_4, count_4));
		ret.add(new VolumeAlarmes(5, nome_5, count_5));

		return ret;
	}

	@PostMapping("/eventos/periodo")
	public ArrayList<Evento> recuperarPorPeriodo(@RequestBody consultaPeriodo periodo){
		try {
			Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getInicio());
			Date fim    = new SimpleDateFormat("yyyy-MM-dd").parse(periodo.getFim());
			ArrayList<Evento> lista = dao.findByDataBetweenOrderByData(inicio, fim);
			return lista;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	
}
