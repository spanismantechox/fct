package com.app.fct.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Id;

import org.json.JSONObject;

import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	private String nombre;
	
	private String contrasena;
	
	@Enumerated(EnumType.STRING)
    private Rol rol;

	
	public JSONObject userToJSON() {
		JSONObject json = new JSONObject();
		json.put("dni", this.nombre);
		
		
		return json;
	}
	
	
}
