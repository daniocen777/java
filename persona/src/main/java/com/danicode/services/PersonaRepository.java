package com.danicode.services;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.danicode.Persona;

public interface PersonaRepository extends Repository<Persona, Integer> {

	List<Persona> findAll();

	//Persona findOne(int id);

	Persona save(Persona persona);

	void delete(Persona persona);

}
