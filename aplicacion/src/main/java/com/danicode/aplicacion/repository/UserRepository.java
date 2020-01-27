package com.danicode.aplicacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danicode.aplicacion.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	

}
