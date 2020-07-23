package com.bruno.projetofinal.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bruno.projetofinal.dto.VolumeAlarmes;
import com.bruno.projetofinal.dto.VolumeEquipamentos;
import com.bruno.projetofinal.model.Evento;

public interface EventoDAO extends CrudRepository<Evento, Integer> {

	public ArrayList<Evento> findByOrderByDataDesc();

	public ArrayList<Evento> findByOrderByDataAsc();
	
	public ArrayList<Evento> findByDataBetweenOrderByData(Date inicio, Date fim);
	
	@Query("Select new com.bruno.projetofinal.dto.VolumeAlarmes(ev.alarme.id, ev.alarme.nome, count(ev.alarme.id)) "
			+ "FROM Evento ev GROUP BY ev.alarme.id ORDER BY count(ev.alarme.id) DESC")
	public ArrayList<VolumeAlarmes> getAllWithName();
	
	@Query("Select new com.bruno.projetofinal.dto.VolumeAlarmes(ev.alarme.id, ev.alarme.nome, count(ev.alarme.id)) "
			+ "FROM Evento ev GROUP BY ev.alarme.id ORDER BY count(ev.alarme.id) DESC")
	public ArrayList<VolumeAlarmes> getTopWithName();
	
	@Query("Select new com.bruno.projetofinal.dto.VolumeAlarmes(ev.alarme.id, ev.alarme.nome, count(ev.alarme.id)) "
			+ "FROM Evento ev "
			+ "WHERE ev.data >= :inicio AND ev.data <= :fim "
			+ "GROUP BY ev.alarme.id "
			+ "ORDER BY count(ev.alarme.id) DESC")
	public ArrayList<VolumeAlarmes> getAllWithNameWithPeriod(@Param("inicio") Date inicio, @Param("fim") Date fim);
	
	@Query("Select new com.bruno.projetofinal.dto.VolumeEquipamentos(ev.equipamento.id, "
			+ "ev.equipamento.hostname, count(ev.equipamento.id)) "
			+ "FROM Evento ev "
			+ "WHERE ev.data >= :inicio AND ev.data <= :fim "
			+ " GROUP BY ev.equipamento.id ORDER BY count(ev.equipamento.id) DESC")
	public ArrayList<VolumeEquipamentos> getAllByHostnameWithPeriod(@Param("inicio") Date inicio, @Param("fim") Date fim);
	
}
