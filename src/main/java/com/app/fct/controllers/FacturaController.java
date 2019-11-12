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

import com.app.fct.models.Factura;
import com.app.fct.services.FacturaService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "/factura")

public class FacturaController {
	
	
	@PostMapping(path = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String crear(@RequestBody Factura factura) {
		return this.facturaService.crearFactura(factura);
	}
	
	@GetMapping(path="/facturas/{mesanho}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String facturasMesAnho(@PathVariable String mesanho) {
		return 	this.facturaService.facturasMes(mesanho);
	}
	
	@GetMapping (path="/list" , produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.facturaService.listaFacturas();
	}
	
	
	@GetMapping (path="/pdf/{id}", produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
	public @ResponseBody String crearFactura(@PathVariable int id) {
		return this.facturaService.exportarFactura(id);
	}
	
	@GetMapping(path="/list-month/{mes}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String listaMes(@PathVariable int mes) {
		return this.facturaService.facturasMesCalendario(mes);
	}
	
	
	

	@Autowired
	private FacturaService facturaService;

}
