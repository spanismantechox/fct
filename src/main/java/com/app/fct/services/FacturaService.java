package com.app.fct.services;


import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Factura;
import com.app.fct.models.Cliente;
import com.app.fct.models.Restaurante;
import com.app.fct.repositories.FacturaRepository;
import com.app.fct.repositories.ClienteRepository;
import com.app.fct.repositories.RestauranteRepository;

@Service
public class FacturaService {
	public String crearFactura(Factura factura) {
		JSONObject json = new JSONObject();

		Optional<Restaurante> r = this.restauranteRepository.findById(factura.getIdFactura());

		if (r.isPresent()) {
			Optional<Cliente> p = this.clienteRepository.findClienteById(factura.getIdCliente());
			if (p.isPresent()) {
			
				

				
				this.facturaRepository.save(factura);
				json.put("message", "Factura creada correctamente!");
				json.put("status:200", "OK");

			}
		} else {
			json.put("message", "El restaurante no existe");
		}

		return json.toString();
	}

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;

}
