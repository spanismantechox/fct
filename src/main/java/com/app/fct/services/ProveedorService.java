package com.app.fct.services;

import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.app.fct.models.Proveedor;
import com.app.fct.repositories.ProveedorRepository;

@Service
public class ProveedorService {
	
	
	public String altaProveedor(Proveedor proveedor) {
		JSONObject json = new JSONObject();
		Optional<Proveedor> u = this.proveedorRepository.findProveedorByNombre(proveedor.getNombre());
		
		if(!u.isPresent()) {
			this.proveedorRepository.save(proveedor);
			json.put("message", "Proveedor dado de alta correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "Proveedor ya existe!");
		}
	
			return json.toString();
	}
	
	public String modProveedor(Proveedor proveedor) {
		JSONObject json = new JSONObject();
		Optional<Proveedor> u = this.proveedorRepository.findProveedorById(proveedor.getId());
		
		if(u.isPresent()) {
			Proveedor aux = u.get();
			aux.setNombre(proveedor.getNombre());
			aux.setTelefono(proveedor.getTelefono());
			this.proveedorRepository.save(aux);
			json.put("message", "Proveedor modificado correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "El proveedor no existe!");
		}
	
			return json.toString();
	}
	
	public String listProveedor() {
		JSONArray json = new JSONArray();
		Iterable<Proveedor> provtIt = this.proveedorRepository.findAll();
		Iterator<Proveedor> it = provtIt.iterator();
		while (it.hasNext()) {
			Proveedor prov = it.next();
			json.put(prov.provToJSON());
		}
		return json.toString();
	}
	
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	

}
