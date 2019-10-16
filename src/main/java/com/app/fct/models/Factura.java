package com.app.fct.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contar_facturas")
	@SequenceGenerator(name = "contar_facturas", sequenceName = "FACTURAS_SEQUENCE")
	private int idFactura;
	
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
	
	
	
}
