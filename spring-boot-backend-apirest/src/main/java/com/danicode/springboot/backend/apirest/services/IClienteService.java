package com.danicode.springboot.backend.apirest.services;

import java.util.List;

import com.danicode.springboot.backend.apirest.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);

	public Cliente save(Cliente cliente);

	public void delete(Long id);

}
