package com.app.fct.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.fct.models.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, String>{
	public Optional<Cliente> findClienteByemail(String mail);
	public Optional<Cliente> findClienteById(int idCliente);
}
