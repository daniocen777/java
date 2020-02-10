package com.danicode.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicode.springboot.backend.apirest.entity.Cliente;

// En vez de usar 'CrudRepository', usar JpaRepository para la paginación 
public interface IClienteDao extends JpaRepository<Cliente, Long> {

	// Page<T> findAll(Pageable pageable);

}
