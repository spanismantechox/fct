package com.app.fct.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.fct.models.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, String>{
	public Optional<Proveedor> findProveedorByNombre(String nombre);
	public Optional<Proveedor> findProveedorById(int idProveedor);
}
