package com.danicode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danicode.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
