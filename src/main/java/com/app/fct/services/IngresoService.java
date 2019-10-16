package com.app.fct.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Restaurante;
import com.app.fct.models.Ingreso;
import com.app.fct.repositories.IngresoRepository;
import com.app.fct.repositories.RestauranteRepository;

@Service
public class IngresoService {


	public String anotarIngreso(Ingreso ingreso) {
		JSONObject json = new JSONObject();
		

		Optional<Restaurante> r = this.restauranteRepository.findById(ingreso.getRestauranteId());
		
		
			if (r.isPresent()) {
				this.ingresgoRepository.save(ingreso);
				
				Restaurante res = r.get();
				if(res.getIngresos() == null) {
					res.setIngresos(new HashSet<Ingreso>());
				}
				res.getIngresos().add(ingreso);
				this.restauranteRepository.save(res);
				
				json.put("message", "Ingreso añadido correctamente!");
				json.put("status:200", "OK");
			} else {
				json.put("message", "El restaurante no existe");
			}			
		

		return json.toString();
	}
	public String modIngreso(Ingreso ingreso) {
		JSONObject json = new JSONObject();
		

		Optional<Ingreso> i = this.ingresgoRepository.findById(ingreso.getIdIngreso());
		
		
			if (i.isPresent()) {
				this.ingresgoRepository.save(ingreso);
				
				
				
				json.put("message", "Ingreso modificado correctamente!");
				json.put("status:200", "OK");
			} else {
				json.put("message", "El ingreso no existe");
			}			
		

		return json.toString();
	}
	
	
	
	public String listIngreso() {
		JSONArray json = new JSONArray();
		Iterable<Ingreso> ingIt = this.ingresgoRepository.findAll();
		Iterator<Ingreso> it = ingIt.iterator();
		while (it.hasNext()) {
			Ingreso ing = it.next();
			json.put(ing.ingToJSON());
		}
		return json.toString();
	}

	
	
	@Autowired
	private IngresoRepository ingresgoRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;
	
}
