package com.danicode.aplicacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danicode.aplicacion.entity.Role;

@Repository
public interface RolRepository extends CrudRepository<Role, Long> {

}
