package com.app.fct.models;

import java.util.Date;

public interface RelFactura {

	int getNumeroFactura();
	Date getFecha();
	String getConcepto();
	String getNombreRestaurante();
	String getNombreCliente();
	int getTotalFactura();
}
