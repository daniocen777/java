package com.danicode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicode.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
