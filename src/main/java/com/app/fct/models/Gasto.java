package com.app.fct.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_gastos")
	@SequenceGenerator(name = "contar_gastos", sequenceName = "GASTOS_SEQUENCE")
	private int idGasto;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;

	private double cantidad;

	@Transient
	private int idRestaurante;

	@Transient
	private int idProveedor;

	public JSONObject gasToJSON() {
		JSONObject json = new JSONObject();

		json.put("fecha", this.fecha);
		json.put("cantidad", this.cantidad);
		json.put("idRestaurante",this.idRestaurante);
		json.put("idProveedor",this.idProveedor);
		return json;
	}

}
