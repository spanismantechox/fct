package com.app.fct.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.fct.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	public Optional<Usuario> findUsuarioByNombre(String nombre);
}
