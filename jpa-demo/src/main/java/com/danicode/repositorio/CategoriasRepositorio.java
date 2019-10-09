package com.danicode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicode.model.Categoria;

//public interface CategoriasRepositorio extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepositorio extends JpaRepository<Categoria, Integer> {

}
