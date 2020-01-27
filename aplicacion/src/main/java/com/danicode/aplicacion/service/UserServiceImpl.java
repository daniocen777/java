package com.danicode.aplicacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danicode.aplicacion.entity.User;
import com.danicode.aplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	// Inyectando el repo del usuario
	@Autowired
	UserRepository userRepository;

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}
