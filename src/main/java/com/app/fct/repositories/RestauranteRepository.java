package com.app.fct.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.fct.models.Restaurante;

public interface RestauranteRepository  extends CrudRepository<Restaurante, Integer>{
	public Optional<Restaurante> findRestauranteByNombre(String nombre);
}
