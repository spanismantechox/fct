package com.app.fct.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger logger = LogManager.getLogger(IngresoService.class);

	public String anotarIngreso(Ingreso ingreso) {
		JSONObject json = new JSONObject();

		Optional<Restaurante> r = this.restauranteRepository.findById(ingreso.getRestauranteId());

		if (r.isPresent()) {
			this.ingresgoRepository.save(ingreso);

			Restaurante res = r.get();
			if (res.getIngresos() == null) {
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
			Ingreso aux = i.get();
			aux.setCantidad(aux.getCantidad());
			aux.setFecha(ingreso.getFecha());
			aux.setFuente(ingreso.getFuente());
			aux.setIdIngreso(ingreso.getIdIngreso());
			aux.setRestauranteId(ingreso.getRestauranteId());
			this.ingresgoRepository.save(aux);

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
			obj = new JSONObject();
			obj.put("idIngreso", ing.getIdIngreso());
			obj.put("fecha", ing.getFecha());
			obj.put("cantidad", ing.getCantidad());
			obj.put("fuente", ing.getFuente());
			obj.put("nombre_restaurante", ing.getNombreRestaurante());
			obj.put("idRestaurante", ing.getIdRestaurante());

			json.put(obj);

		}

		JSONArray restaurante = new JSONArray();
		Iterable<Restaurante> restIt = this.restauranteRepository.findAll();
		Iterator<Restaurante> itRes = restIt.iterator();
		while (itRes.hasNext()) {
			Restaurante r = itRes.next();
			restaurante.put(r.restToJSON());
		}

		res.put("restaurantes", restaurante);
		res.put("ingreso", json);

		return res.toString();

	}

	public String delIngreso(int idIngreso) {

		JSONObject json = new JSONObject();

		Optional<Ingreso> i = this.ingresgoRepository.findById(idIngreso);
		if (i.isPresent()) {
			this.ingresgoRepository.delete(i.get());
			json.put("message", "Ingreso eliminado correctamnte");
		} else {
			json.put("message", "Este ingreso no existe!");
		}
		return json.toString();
	}

	public String fuentesIngreso(String periodo, int numero) {
		JSONObject json = new JSONObject();
		
			String[] fuentes = Arrays.asList(Fuente.values()).stream().map(f -> f.toString()).toArray(String[]::new);
			Integer t=null;
			for (String f : fuentes) {
				if (periodo.equals("anual")) {
					t = this.ingresgoRepository.getTotalIngresoAnual(numero,f);
					logger.info("Entro bien al anual");
				}
				else if (periodo.equals("mensual")) {
					t = this.ingresgoRepository.getTotalIngresoMensual(numero, f);
					logger.info("Recibo bien el numero"+numero);
				
				}
				else {
					t = this.ingresgoRepository.getTotalIngreso(f);
					logger.info("Entro bien al total");
				}
				if (t != null) {
					json.put(f, t);
				} else {
					json.put(f, 0);
				}
			}
	
		return json.toString();

	}
	public String fuentesIngresoId(String periodo,int numero, int id) {
		JSONObject json = new JSONObject();
		String[]fuentes=Arrays.asList(Fuente.values()).stream().map(f->f.toString()).toArray(String[]::new);
		Integer t = null;
		
		for (String f: fuentes) {
			if( periodo.equals("mensual")) {
				t = this.ingresgoRepository.getTotalIngresoMensualId(numero, f, id);
				logger.info("recibo bien los datos mensuales "+numero,f,id);
			}
			else if(periodo.equals("anual")) {
				t = this.ingresgoRepository.getTotalIngresoAnualId(numero, f, id);
				logger.info("recibo bien los datos anuales"+numero,f,id);
			}
			else if(periodo.equals("mensual")) {
				t = this.ingresgoRepository.getTotalIngresoMensualId(numero, f, id);
				logger.info("recibo bien los datos mensuales"+numero,f,id);
			}
			else if(periodo.equals("anual")) {
				t = this.ingresgoRepository.getTotalIngresoAnualId(numero, f, id);
				logger.info("recibo bien los datos anuales ",numero+"fuente",f+"id",id);
			}
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
