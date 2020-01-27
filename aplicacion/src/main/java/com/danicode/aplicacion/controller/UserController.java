package com.danicode.aplicacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.danicode.aplicacion.entity.User;
import com.danicode.aplicacion.repository.RolRepository;
import com.danicode.aplicacion.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// Se usa el repo y no el servicem ya que sólo queremos de él la lista
	@Autowired
	RolRepository roleRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab", "active");
		model.addAttribute("formTab", "deactive");
		return "user-form/user-view";
	}

}
