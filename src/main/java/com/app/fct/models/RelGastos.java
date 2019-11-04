package com.app.fct.models;

import java.util.Date;


public interface  RelGastos {
	int getIdGasto();
	String getNombreProveedor();
	Date getFecha();
	double getCantidad();
	String getNombre();
	int getIdProveedor();
	int getIdRestaurante();
}
