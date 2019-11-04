package com.app.fct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.fct.models.Restaurante;
import com.app.fct.services.RestauranteService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/restaurante")
public class RestauranteController {
	
	@PostMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String alta(@RequestBody Restaurante restaurante) {
		return this.restauranteService.altaRestaurante(restaurante);
	}
	
	@PostMapping(path = "/mod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mod(@RequestBody Restaurante restaurante) {
		return this.restauranteService.modRestaurante(restaurante);
	}
	
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.restauranteService.listRestaurante();
	}
	
	@GetMapping(path="/most/{idRestaurante}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String most(@PathVariable int idRestaurante) {
		return this.restauranteService.datosRestaurante(idRestaurante);
	}
	

	@Autowired
	private RestauranteService restauranteService;
}
