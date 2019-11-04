package com.app.fct.models;

import java.util.Date;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.json.JSONObject;

import lombok.Data;

@Entity
@Data
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_facturas")
	@SequenceGenerator(name = "contar_facturas", sequenceName = "FACTURAS_SEQUENCE")
	private int id;

	private Date fecha;

	private String concepto;

	private String iva;

	private int totalConcepto;

	private int totalFactura;

	@OneToMany
	private Set<Gasto> gastos;

	@Transient
	private int idRestaurante;

	@Transient
	private int idCliente;

	public JSONObject facToJSON() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("cantidad", this.concepto);
		json.put("iva", this.iva);
		json.put("fecha", this.fecha);
		json.put("totalConcepto", this.totalConcepto);
		json.put("totalFactura", this.totalFactura);
		return json;
	}

}
