package com.app.fct.models;

import java.util.Date;

public interface RelIngreso {
	int getIdIngreso();
	Date getFecha();
	double getCantidad();
	String getFuente();
	String getNombreRestaurante();
	int getIdRestaurante();

}
