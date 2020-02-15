package com.danicode.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danicode.springboot.backend.apirest.entity.Cliente;
import com.danicode.springboot.backend.apirest.entity.Region;

// En vez de usar 'CrudRepository', usar JpaRepository para la paginación 
public interface IClienteDao extends JpaRepository<Cliente, Long> {

	// Como se quiere sólo la lista de regiones, no es necesario crear otra
	// Interface
	// Si se quiere hacer un DAO de regiones, sí hacer una interface

	@Query("from Region")
	public List<Region> findAllRegiones();

	// Page<T> findAll(Pageable pageable);

}
