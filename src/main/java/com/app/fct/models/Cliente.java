package com.app.fct.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.json.JSONObject;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_cli")
	@SequenceGenerator(name = "contar_cli", sequenceName = "CLI_SEQUENCE")
	private int id;
	
	private String nombre;
	
	private String email;
	
	private String cif;
	
	private String direccion;
	
	private String cp;
	
	
	@OneToMany
	private Set<Factura> facturas;
	
	
	public JSONObject cliToJSON() {
		JSONObject json = new JSONObject();
		json.put("id",this.id);
		json.put("nombre", this.nombre);
		json.put("email", this.email);
		json.put("cif", this.cif);
		json.put("direccion", this.direccion);
		json.put("cp", this.cp);
		
		return json;
	}	
	
	public JSONObject clienteTOJSON() {
		JSONObject json= new JSONObject();
		json.put("email", this.email);
		json.put("cif", this.cif);
		json.put("direccion", this.direccion);
		json.put("cp", this.cp);
		
		return json;
	}
	
}
