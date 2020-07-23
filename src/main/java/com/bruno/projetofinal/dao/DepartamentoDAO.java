package com.bruno.projetofinal.dao;

import org.springframework.data.repository.CrudRepository;

import com.bruno.projetofinal.model.Departamento;

public interface DepartamentoDAO 
				extends CrudRepository< Departamento		,	Integer> {
	//				    		 		classe que manipula	,	tipo do dado da chave primaria

}