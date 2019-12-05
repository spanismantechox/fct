package com.app.fct.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.app.fct.models.Gasto;
import com.app.fct.models.RelGastos;

public interface GastoRepository extends CrudRepository<Gasto, Integer>{

	
	
	@Query(value = "SELECT g.cantidad, g.fecha, prov.nombre as nombreProveedor, res.nombre , p.proveedor_id as idProveedor, r.restaurante_id as idRestaurante, g.id_gasto as idGasto FROM gasto g join proveedor_gastos p on g.id_gasto = p.gastos_id_gasto join proveedor prov on p.proveedor_id = prov.id join restaurante_gastos r on g.id_gasto = r.gastos_id_gasto join restaurante res on res.id = r.restaurante_id ORDER BY fecha DESC", nativeQuery = true)    
	public List<RelGastos> getRestauranteGasto();
	
	
}
