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

import com.app.fct.models.Gasto;
import com.app.fct.services.GastoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/gasto")

public class GastoController {

	
	@PostMapping(path = "/salida", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String salida(@RequestBody Gasto gasto) {
		return this.gastoService.anotarSalida(gasto);
	}
	
	@PostMapping(path = "/mod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mod(@RequestBody Gasto gasto) {
		return this.gastoService.modGasto(gasto);
	}
	
	@GetMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.gastoService.listGastos();	
	}

	@Autowired
	private GastoService gastoService;

	
}
