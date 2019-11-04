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
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_prov")
	@SequenceGenerator(name = "contar_prov", sequenceName = "PROV_SEQUENCE")
	private int id;
	
	private String nombre;
	
	private String telefono;
	
	@OneToMany
    private Set<Factura> factura;

	@OneToMany
	private Set<Gasto> gastos;
	
	
	
	
	public JSONObject provToJSON() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("nombre", this.nombre);
		json.put("telefono", this.telefono);		
		return json;
	}
}
