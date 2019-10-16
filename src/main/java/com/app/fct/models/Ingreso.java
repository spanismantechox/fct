package com.app.fct.models;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.json.JSONObject;

import lombok.Data;

@Entity
@Data
public class Ingreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_ingreso")
	@SequenceGenerator(name = "contar_ingreso", sequenceName = "INGRESOS_SEQUENCE")
	private int idIngreso;
	
	private double cantidad;
	
	@Enumerated(EnumType.STRING)
	private Fuente fuente;
	
	private Date fecha;

	@Transient
	private int restauranteId;
	
	
	public JSONObject ingToJSON() {
		JSONObject json = new JSONObject();
		
		json.put("cantidad", this.cantidad);
		json.put("fuente", this.fuente);
		json.put("fecha", this.fecha);		
		return json;
	}

}
