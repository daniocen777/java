package com.danicode.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.danicode.springboot.backend.apirest.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
