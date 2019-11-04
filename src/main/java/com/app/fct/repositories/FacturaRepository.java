package com.app.fct.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.fct.models.CrearFactura;
import com.app.fct.models.Factura;
import com.app.fct.models.RelFactura;

public interface FacturaRepository  extends CrudRepository<Factura, String>{
	
	public Optional<Factura> findFacturaById(int id);

	@Query(value = "from Factura t where fecha BETWEEN :startDate AND :endDate")
	public List<Factura> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	@Query(value="SELECT f.id as numeroFactura, f.fecha as fecha, f.concepto as concepto , res.nombre as nombreRestaurante, c.nombre as nombreCliente, f.total_factura as totalFactura FROM factura f join restaurante_facturas r on r.facturas_id = f.id join cliente_facturas cli on cli.facturas_id=r.facturas_id join restaurante res on res.id = r.restaurante_id join cliente c on c.id = cli.cliente_id", nativeQuery = true)
	public List<RelFactura> getListaRestaurante();
	
	@Query(value="SELECT f.id as numeroFactura, f.iva as iva,f.fecha as fecha, f.concepto as concepto, f.total_concepto as totalC, f.total_factura as totalF, res.cif as cif, res.cp as cp, res.direccion as direccion, res.nombre as nombreRest, res.nombre_fiscal as nombre, res.telefono as tel, c.cif as cifC, c.cp as cpC, c.direccion as dir, c.email as email, c.nombre as nombreCli FROM factura f join restaurante_facturas r on r.facturas_id = f.id join cliente_facturas cli on cli.facturas_id=r.facturas_id join restaurante res on res.id = r.restaurante_id join cliente c on c.id = cli.cliente_id  where f.id = :idFactura",nativeQuery = true)
	public List<CrearFactura> getDatosFactura(@Param("idFactura")int idFactura);


}
