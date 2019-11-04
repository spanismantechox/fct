package com.app.fct.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Restaurante;
import com.app.fct.models.Fuente;
import com.app.fct.models.Ingreso;
import com.app.fct.models.RelIngreso;
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
				
				json.put("message", "ok");
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
		JSONObject res = new JSONObject();
		JSONArray json = new JSONArray();
		List<RelIngreso> ingreso = this.ingresgoRepository.getRestauranteIngreso();
		Iterator<RelIngreso> it = ingreso.iterator();
		JSONObject obj;
		while (it.hasNext()) {
			RelIngreso ing = it.next();
			obj= new JSONObject();
			obj.put("idIngreso", ing.getIdIngreso());
			obj.put("fecha",ing.getFecha());
			obj.put("cantidad", ing.getCantidad());
			obj.put("fuente", ing.getFuente());
			obj.put("nombre_restaurante", ing.getNombreRestaurante());
			obj.put("idRestaurante", ing.getIdRestaurante());
			
			json.put(obj);
			
		}
		
		JSONArray restaurante = new JSONArray();
		Iterable<Restaurante> restIt= this.restauranteRepository.findAll();
		Iterator<Restaurante> itRes= restIt.iterator();
		while(itRes.hasNext()) {
			Restaurante r = itRes.next();
			restaurante.put(r.restToJSON());
		}
		
		res.put("restaurantes", restaurante);
		res.put("ingreso",json);
		
		return res.toString();
		
	}
	
	
	public String delIngreso(int idIngreso) {
		
		JSONObject json = new JSONObject();
		
		Optional<Ingreso> i = this.ingresgoRepository.findById(idIngreso);
		if(i.isPresent()) {
			this.ingresgoRepository.delete(i.get());
			json.put("message", "Ingreso eliminado correctamnte");
		}else {
			json.put("message","Este ingreso no existe!");
		}
		return json.toString();
	}
	
	
	
	public String fuentesIngreso() {
		JSONObject json = new JSONObject();
		
		String [] fuentes = Arrays.asList(Fuente.values())
	            .stream()
	            .map(f -> f.toString())
	            .toArray(String[]::new);
		
		for(String f : fuentes) {
			Integer t = this.ingresgoRepository.getTotalIngreso(f);
			if (t != null) {
				json.put(f, t);
			} else {
				json.put(f, 0);
			}
		}
		return json.toString();
	}
	

	
	
	@Autowired
	private IngresoRepository ingresgoRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;
	
}
