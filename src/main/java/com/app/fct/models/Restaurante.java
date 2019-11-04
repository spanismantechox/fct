package com.app.fct.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.json.JSONObject;

import lombok.Data;

@Entity
@Data
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_rest")
	@SequenceGenerator(name = "contar_rest", sequenceName = "RES_SEQUENCE")
	private int id;
	
	private String nombre;

	private String direccion;
	
	private String telefono;
	
	private String cif;
	
	private String cp;
	
	private String nombreFiscal;
		
	@OneToMany
	private Set<Factura> facturas;
	
	@OneToMany
	private Set<Gasto> gastos;
	
	@OneToMany
	private Set<Ingreso> ingresos;

	@ManyToMany
	private Set<Usuario> usuarios;
	
	@ManyToMany
	private Set<Proveedor> proveedores;
	
	
	
	
	public JSONObject restToJSON() {
		JSONObject json = new JSONObject();
		json.put("id",this.id);
		json.put("nombre", this.nombre);
		json.put("direccion", this.direccion);
		json.put("telefono", this.telefono);
		json.put("cif", this.cif);
		json.put("cp", this.cp);
		json.put("nombreFiscal", this.nombreFiscal);
		
		
		return json;
	}
	
	
	public  JSONObject restauranteToJSON(){
		JSONObject json = new JSONObject();{
			json.put("direccion", this.direccion);
			json.put("telefono", this.telefono);
			json.put("cif", this.cif);
			json.put("cp", this.cp);
			json.put("nombreFiscal", this.nombreFiscal);
			
		}
		return json;
		
	}
}
