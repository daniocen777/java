package com.danicode.services;

import java.util.List;

import com.danicode.Persona;

public interface PersonaService {

	List<Persona> listar();

	Persona listarId(int id);

	Persona add(Persona persona);

	Persona edit(Persona persona);

	Persona delete(int id);

}
