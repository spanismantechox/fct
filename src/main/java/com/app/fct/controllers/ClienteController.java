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

import com.app.fct.models.Cliente;
import com.app.fct.services.ClienteService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/cliente")

public class ClienteController {
	@PostMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String alta(@RequestBody Cliente cliente) {
		return this.clienteService.altaCliente(cliente);
	}

	
	@PostMapping(path = "/mod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mod(@RequestBody Cliente cliente) {
		return this.clienteService.modCliente(cliente);	
	}
	
	@GetMapping(path = "/list",  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.clienteService.listCliente();	
	}
	
	@Autowired
	private ClienteService clienteService;

}
