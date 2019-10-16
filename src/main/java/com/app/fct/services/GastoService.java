package com.app.fct.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.fct.models.Proveedor;
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

				json.put("message", "Gasto añadido correctamente!");
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
				this.gastoRepository.save(gasto);
				json.put("message", "Gasto modificado correctamente!");
				json.put("status:200", "OK");
			
		} else {
			json.put("message", "El gasto no existe");
		}

		return json.toString();
	}
	
	
	public String listGastos() {
		JSONArray json = new JSONArray();
		Iterable<Gasto> gastIt = this.gastoRepository.findAll();
		Iterator<Gasto> it = gastIt.iterator();
		while (it.hasNext()) {
			Gasto prov = it.next();
			json.put(prov.gasToJSON());
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
