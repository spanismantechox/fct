package com.app.fct.services;

import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Restaurante;

import com.app.fct.repositories.RestauranteRepository;


@Service
public class RestauranteService {

	public String altaRestaurante(Restaurante restaurante) {
		JSONObject json = new JSONObject();
		Optional<Restaurante> u = this.restauranteRepository.findRestauranteByNombre(restaurante.getNombre());
		
		if(!u.isPresent()) {
			this.restauranteRepository.save(restaurante);
			json.put("message", "Restaurante dado de alta correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "Restaurante ya existe!");
		}

		return json.toString();
	}
	
	public String modRestaurante(Restaurante restaurante) {
		JSONObject json = new JSONObject();
		Optional<Restaurante> u = this.restauranteRepository.findById(restaurante.getId());
		
		if(u.isPresent()) {
			this.restauranteRepository.save(restaurante);
			json.put("message", "Restaurante modificado correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "El restaurante no existe!");
		}

		return json.toString();
	}
	
	public String listRestaurante() {
		JSONArray json = new JSONArray();

		Iterable<Restaurante> restIt = this.restauranteRepository.findAll();
		Iterator<Restaurante> it = restIt.iterator();
		while (it.hasNext()) {
			Restaurante us = it.next();
			json.put(us.restToJSON());
		}
		return json.toString();

	}
	
	
	public String datosRestaurante(int id) {
		JSONObject json = new JSONObject();
		Optional<Restaurante> r = this.restauranteRepository.findById(id);
		
		if(r.isPresent()) {		
			json.put("restaurante", r.get().restauranteToJSON());
		} else {
			json.put("message", "El restaurante no existe");
		}
		
		return json.toString();
		
	}
	
	
	@Autowired
	private RestauranteRepository restauranteRepository;
}
