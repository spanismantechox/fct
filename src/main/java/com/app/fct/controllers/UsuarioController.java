package com.app.fct.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.fct.models.Usuario;
import com.app.fct.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/usuario")

public class UsuarioController {
	
	
	@PostMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String alta(@RequestBody Usuario usuario) {
		return this.usuarioService.altaUsuario(usuario);
	}

	@PostMapping(path = "/mod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mod(@RequestBody Usuario usuario) {
		return this.usuarioService.modUsuario(usuario);
	}
	
	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String login(@RequestBody Usuario usuario) {
		return this.usuarioService.loginUsuarios(usuario);
	}
	
	
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.usuarioService.listUsuarios();
	}
	
	@Autowired
	private UsuarioService usuarioService;

}
