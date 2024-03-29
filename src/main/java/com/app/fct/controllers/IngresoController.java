package com.app.fct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.fct.models.Ingreso;
import com.app.fct.services.IngresoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping(path = "/ingreso")

public class IngresoController {
	
	
	
	@PostMapping(path = "/ingresar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String ingresar(@RequestBody Ingreso ingreso) {
		return this.ingresoService.anotarIngreso(ingreso);
	}
	
	@PostMapping(path = "/mod", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String mod(@RequestBody Ingreso ingreso) {
		return this.ingresoService.modIngreso(ingreso);
	}
	
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list() {
		return this.ingresoService.listIngreso();	
	}
	
	@DeleteMapping( path= "/del/{idIngreso}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String delIngreso(@PathVariable int idIngreso) {
		return this.ingresoService.delIngreso(idIngreso);
	}
	
	@GetMapping(path = "/fuentes-ingreso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String fuentesIngreso(@RequestParam(value = "periodo") String periodo, @RequestParam(value = "numero" , defaultValue = "0") int numero) {
		return this.ingresoService.fuentesIngreso(periodo, numero);	
	}
	@GetMapping(path = "/fuentes-ingreso-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String fuentesIngreso(@RequestParam(value = "periodo") String periodo, @RequestParam(value = "numero" , defaultValue = "0") int numero, @RequestParam(value="id")int id) {
		return this.ingresoService.fuentesIngresoId(periodo, numero,id);	
	}
	@Autowired
	private IngresoService ingresoService;       

}
