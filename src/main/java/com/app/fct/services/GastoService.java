package com.app.fct.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.fct.models.Proveedor;
import com.app.fct.models.RelGastos;
import com.app.fct.models.Gasto;
import com.app.fct.models.Restaurante;
import com.app.fct.repositories.GastoRepository;
import com.app.fct.repositories.ProveedorRepository;
import com.app.fct.repositories.RestauranteRepository;

@Service
public class GastoService {

	public String anotarSalida(Gasto gasto) {
		JSONObject json = new JSONObject();

		Optional<Restaurante> r = this.restauranteRepository.findById(gasto.getIdRestaurante());

		if (r.isPresent()) {
			Optional<Proveedor> p = this.proveedorRepository.findProveedorById(gasto.getIdProveedor());
			if (p.isPresent()) {
				this.gastoRepository.save(gasto);

				Restaurante res = r.get();
				if (res.getGastos() == null) {
					res.setGastos(new HashSet<Gasto>());
				}
				res.getGastos().add(gasto);
				this.restauranteRepository.save(res);

				json.put("message", "ok");
				json.put("status:200", "OK");

				Proveedor prov = p.get();
				if (prov.getGastos() == null) {
					prov.setGastos(new HashSet<Gasto>());
				}
				prov.getGastos().add(gasto);
				this.proveedorRepository.save(prov);
			} else {
				json.put("message", "El proveedor no existe");
			}
		} else {
			json.put("message", "El restaurante no existe");
		}

		return json.toString();
	}

	public String modGasto(Gasto gasto) {
		JSONObject json = new JSONObject();
		
		Optional<Gasto> g = this.gastoRepository.findById(gasto.getIdGasto());
		
		if (g.isPresent()) {
			Gasto aux=g.get();
			aux.setCantidad(gasto.getCantidad());
			aux.setFecha(gasto.getFecha());
			aux.setIdRestaurante(gasto.getIdRestaurante());
			aux.setIdProveedor(gasto.getIdProveedor());
			this.gastoRepository.save(aux);

			

			json.put("message", "Gasto modificado correctamente!");
			json.put("status:200", "OK");

		} else {
			json.put("message", "El gasto no existe");
		}

		return json.toString();
	}

	public String listGastos() {
		JSONObject res = new JSONObject();
		JSONArray json = new JSONArray();
		List<RelGastos> gastos = this.gastoRepository.getRestauranteGasto();
		Iterator<RelGastos> it = gastos.iterator();
		JSONObject obj;
		while (it.hasNext()) {
			RelGastos prov = it.next();
			obj = new JSONObject();
			obj.put("nombre_proveedor", prov.getNombreProveedor());
			obj.put("fecha", prov.getFecha());
			obj.put("cantidad", prov.getCantidad());
			obj.put("nombre_restaurante", prov.getNombre());
			obj.put("idProveedor", prov.getIdProveedor());
			obj.put("idRestaurante", prov.getIdRestaurante());
			obj.put("idGasto", prov.getIdGasto());

			json.put(obj);
		}

		JSONArray restaurantes = new JSONArray();
		Iterable<Restaurante> restIt = this.restauranteRepository.findAll();
		Iterator<Restaurante> itRes = restIt.iterator();
		while (itRes.hasNext()) {
			Restaurante us = itRes.next();
			restaurantes.put(us.restToJSON());
		}

		JSONArray proveedores = new JSONArray();
		Iterable<Proveedor> provIt = this.proveedorRepository.findAll();
		Iterator<Proveedor> itProv = provIt.iterator();
		while (itProv.hasNext()) {
			Proveedor prov = itProv.next();
			proveedores.put(prov.provToJSON());
		}

		res.put("restaurantes", restaurantes);
		res.put("proveedores", proveedores);
		res.put("gastos", json);

		return res.toString();
	}
	
	
	public String delGasto(int idGasto) {
		JSONObject json = new JSONObject();
		
		Optional <Gasto> g = this.gastoRepository.findById(idGasto);
		if(g.isPresent()) {
			this.gastoRepository.delete(g.get());
			json.put("message","Gasto eliminado correctamente");
		}else {
			json.put("message", "Este gasto no existe!");
		}
		return json.toString();
	}

	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;

}
