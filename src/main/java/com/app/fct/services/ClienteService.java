package com.app.fct.services;

import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Cliente;
import com.app.fct.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	public String altaCliente(Cliente cliente) {
		JSONObject json = new JSONObject();
		Optional<Cliente> u = this.clienteRepository.findClienteByemail(cliente.getEmail());
		
		if(!u.isPresent()) {
			this.clienteRepository.save(cliente);
			json.put("message", "Cliente dado de alta correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "Cliente ya existe!");
		}
	
			return json.toString();
	}
	
	public String modCliente(Cliente cliente) {
		JSONObject json = new JSONObject();
		Optional<Cliente> u = this.clienteRepository.findClienteById(cliente.getId());
		
		if(u.isPresent()) {
			Cliente aux = u.get();
			aux.setNombre(cliente.getNombre());
			aux.setDireccion(cliente.getDireccion());
			aux.setCp(cliente.getCp());
			aux.setCif(cliente.getCif());
			aux.setEmail(cliente.getEmail());
			this.clienteRepository.save(aux);
			json.put("message", "Cliente modificado correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "El cliente no existe!");
		}
	
			return json.toString();
	}
	
	public String listCliente() {
		JSONArray json = new JSONArray();
		Iterable<Cliente> restIt = this.clienteRepository.findAll();
		Iterator<Cliente> it = restIt.iterator();
		while (it.hasNext()) {
			Cliente cli = it.next();
			json.put(cli.cliToJSON());
		}
		return json.toString();
	}
	
	public String datosCliente(int id) {
		JSONObject json= new JSONObject();
		Optional<Cliente> c =this.clienteRepository.findClienteById(id);
		
		
		if(c.isPresent()) {
			json.put("cliente", c.get().clienteTOJSON());
		}else {
			json.put("message","el cliente no existe!");
		}
		
		return json.toString();
	}
	
	
	@Autowired
	private ClienteRepository clienteRepository;

}
