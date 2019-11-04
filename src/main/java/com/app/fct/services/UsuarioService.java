package com.app.fct.services;

import java.util.Iterator;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Usuario;
import com.app.fct.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	public String altaUsuario(Usuario usuario) {
		JSONObject json = new JSONObject();
		Optional<Usuario> u = this.usuarioRepository.findUsuarioByNombre(usuario.getNombre().toLowerCase());

		if (!u.isPresent()) {
			usuario.setNombre(usuario.getNombre().toLowerCase());
			this.usuarioRepository.save(usuario);
			json.put("message", "Usuario dado de alta correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "Usuario ya existe!");
		}

		return json.toString();
	}

	public String modUsuario(Usuario usuario) {
		JSONObject json = new JSONObject();
		Optional<Usuario> u = this.usuarioRepository.findUsuarioByNombre(usuario.getNombre().toLowerCase());

		if (u.isPresent()) {
			this.usuarioRepository.save(usuario);
			json.put("message", "Usuario modificado correctamente!");
			json.put("status:200", "OK");
		} else {
			json.put("message", "El usuario no existe!");
		}

		return json.toString();
	}

	public String loginUsuarios(Usuario usuario) {
		JSONObject json = new JSONObject();

		Optional<Usuario> u = this.usuarioRepository.findById(usuario.getNombre().toLowerCase());

		if (u.isPresent()) {
			Usuario userBBDD = u.get();
			if (usuario.getContrasena().equals(userBBDD.getContrasena())) {
				json.put("message","Logeado");
			} else {
				json.put("message", "Contraseña o nombre incorrectos");
			}
		} else {
			json.put("message", "Contraseña o nombre incorrectos");
		}

		return json.toString();
	}

	public String listUsuarios() {
		JSONArray json = new JSONArray();

		Iterable<Usuario> usuariosIt = this.usuarioRepository.findAll();
		Iterator<Usuario> it = usuariosIt.iterator();
		while (it.hasNext()) {
			Usuario us = it.next();
			json.put(us.userToJSON());
		}
		return json.toString();

	}

	@Autowired
	private UsuarioRepository usuarioRepository;

}
