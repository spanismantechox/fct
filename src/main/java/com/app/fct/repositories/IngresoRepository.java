package com.app.fct.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.fct.models.Ingreso;
import com.app.fct.models.RelIngreso;

public interface IngresoRepository extends CrudRepository<Ingreso, Integer>{
	
	@Query(value="SELECT i.id_ingreso as idIngreso, i.fecha, i.cantidad, i.fuente, res.nombre as nombreRestaurante , r.ingresos_id_ingreso as idRestaurante FROM ingreso i join restaurante_ingresos r on r.ingresos_id_ingreso = i.id_ingreso join restaurante res on res.id=r.restaurante_id", nativeQuery=true )
	public List<RelIngreso> getRestauranteIngreso();
	
	
	@Query(value="SELECT SUM(cantidad) FROM ingreso i WHERE i.fuente=:fuente", nativeQuery=true )
	public Integer getTotalIngreso(@Param("fuente")String fuente);
	
	@Query(value="SELECT SUM(cantidad) FROM ingreso WHERE  DATE_FORMAT(fecha,'%Y')=:numero AND fuente=:fuente",nativeQuery=true )
	public Integer getTotalIngresoAnual(@Param("numero")int numero, @Param("fuente")String fuetne);
	
	
	@Query(value="SELECT SUM(cantidad) FROM ingreso WHERE DATE_FORMAT(fecha,'%m')=:numero AND fuente=:fuente", nativeQuery=true)
	public Integer getTotalIngresoMensual(@Param("numero")int numero, @Param("fuente")String fuente);
	
	@Query(value="SELECT SUM(cantidad), res.nombre as nombreRestaurante FROM ingreso JOIN restaurante_ingresos r on r.ingresos_id_ingreso = ingreso.id_ingreso JOIN restaurante res on res.id = r.restaurante_id WHERE DATE_FORMAT(fecha,'%m')=:numero AND fuente=:fuente AND res.id=:id",nativeQuery=true)
	public Integer getTotalIngresoMensualId(@Param("numero")int numero, @Param("fuente")String fuente, @Param("id")int id);
	
	@Query(value="SELECT SUM(cantidad), res.nombre as nombreRestaurante FROM ingreso JOIN restaurante_ingresos r on r.ingresos_id_ingreso = ingreso.id_ingreso JOIN restaurante res on res.id=r.restaurante_id WHERE DATE_FORMAT(fecha,'%Y')=:numero AND fuente=:fuente AND res.id=:id",nativeQuery=true)
	public Integer getTotalIngresoAnualId(@Param("numero")int numero, @Param("fuente")String fuente, @Param("id")int id);
}
