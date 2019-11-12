package com.app.fct.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fct.models.Factura;
import com.app.fct.models.RelFactura;
import com.app.fct.models.Cliente;
import com.app.fct.models.CrearFactura;
import com.app.fct.models.Restaurante;
import com.app.fct.repositories.FacturaRepository;
import com.app.fct.repositories.ClienteRepository;
import com.app.fct.repositories.RestauranteRepository;

@Service
public class FacturaService {
	public String crearFactura(Factura factura) {
		JSONObject json = new JSONObject();

		Optional<Restaurante> r = this.restauranteRepository.findById(factura.getIdRestaurante());

		if (r.isPresent()) {
			Optional<Cliente> p = this.clienteRepository.findClienteById(factura.getIdCliente());
			if (p.isPresent()) {

				this.facturaRepository.save(factura);

				Restaurante res = r.get();
				Cliente cli = p.get();
				if (res.getFacturas() == null) {
					res.setFacturas(new HashSet<Factura>());
				}
				res.getFacturas().add(factura);
				if (cli.getFacturas() == null) {
					cli.setFacturas(new HashSet<Factura>());
				}
				cli.getFacturas().add(factura);

				this.restauranteRepository.save(res);
				this.clienteRepository.save(cli);

				json.put("message", "Factura creada correctamente!");
				json.put("status", "OK");

			} else {
				json.put("message", "el cliente no existe!");
			}
		} else {
			json.put("message", "El restaurante no existe");
		}

		return json.toString();
	}

	public String facturasMes(String mesanho) {
		JSONObject json = new JSONObject();

		String mes = mesanho.split("-")[0];
		String anho = mesanho.split("-")[1];

		int m = Integer.parseInt(mes);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(anho));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = cal.getTime();

		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, m - 1);
		cal1.set(Calendar.YEAR, Integer.parseInt(anho));
		cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
		Date endDate = cal1.getTime();

		List<Factura> facturas = this.facturaRepository.getAllBetweenDates(startDate, endDate);

		json.put("facturas", facturas);
		return json.toString();
	}

	public String listaFacturas() {
		JSONObject j = new JSONObject();
		JSONArray json = new JSONArray();
		List<RelFactura> fac = this.facturaRepository.getListaRestaurante();
		Iterator<RelFactura> it = fac.iterator();
		JSONObject obj;
		while (it.hasNext()) {
			RelFactura fact = it.next();
			obj = new JSONObject();
			obj.put("numeroFactura", fact.getNumeroFactura());
			obj.put("fecha", fact.getFecha());
			obj.put("concepto", fact.getConcepto());
			obj.put("nombreRestaurante", fact.getNombreRestaurante());
			obj.put("nombreCliente", fact.getNombreCliente());
			obj.put("totalFactura", fact.getTotalFactura());
			json.put(obj);

		}

		j.put("factura", json);
		return j.toString();
	}

	public String exportarFactura(int id) {
		JSONObject json = new JSONObject();
		Optional<Factura> f = this.facturaRepository.findFacturaById(id);

		if (f.isPresent()) {
			try {
				List<CrearFactura> c = this.facturaRepository.getDatosFactura(id);
				Iterator<CrearFactura> it = c.iterator();
				while (it.hasNext()) {
					CrearFactura cf = it.next();
					PDDocument pDDocument = PDDocument
							.load(new File(getClass().getClassLoader().getResource("plantilla.pdf").getFile()));
					PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
					PDField field = pDAcroForm.getField("nombreFiscal");
					field.setValue(cf.getNombre());
					field = pDAcroForm.getField("cif");
					field.setValue(cf.getCif());
					field = pDAcroForm.getField("direccion");
					field.setValue(cf.getDireccion());
					field = pDAcroForm.getField("cp");
					field.setValue(cf.getCp());
					field = pDAcroForm.getField("telefono");
					field.setValue(cf.getTel());
					field = pDAcroForm.getField("nombreR");
					field.setValue(cf.getNombreRest());
					field = pDAcroForm.getField("cifC");
					field.setValue(cf.getCifC());
					field = pDAcroForm.getField("cpC");
					field.setValue(cf.getCpC());
					field = pDAcroForm.getField("dir");
					field.setValue(cf.getDir());
					field = pDAcroForm.getField("nombre");
					field.setValue(cf.getNombreCli());
					field = pDAcroForm.getField("numFactura");
					field.setValue(cf.getNumeroFactura() + "");
					field = pDAcroForm.getField("concepto");
					field.setValue(cf.getConcepto());
					field = pDAcroForm.getField("iva");
					field.setValue(cf.getIva());
					field = pDAcroForm.getField("ivab");
					field.setValue(cf.getIva());
					field = pDAcroForm.getField("totalC");
					field.setValue(cf.getTotalC() + "");
					field = pDAcroForm.getField("totalCB");
					field.setValue(cf.getTotalC() + "");
					field = pDAcroForm.getField("totalF");
					field.setValue(cf.getTotalF() + "");
					field = pDAcroForm.getField("totalFB");
					field.setValue(cf.getTotalF() + "");
					SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
					String date = DATE_FORMAT.format(cf.getFecha());
					field = pDAcroForm.getField("fecha");
					field.setValue(date);
					field = pDAcroForm.getField("");

					pDDocument.save("factura"+cf.getNumeroFactura()+".pdf");
					pDDocument.close();
					json.put("message", "factura creada con exito!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			json.put("message", "factura inexistente!");
		}

		return json.toString();

	}

	public String facturasMesCalendario(int mes) {
		JSONObject j = new JSONObject();
		
		JSONArray json = new JSONArray();
		List<RelFactura> f = this.facturaRepository.getListaFacturaMes(mes);
		Iterator<RelFactura> it = f.iterator();
		JSONObject obj;
		while (it.hasNext()) {
			RelFactura fact = it.next();
			obj = new JSONObject();
			obj.put("numeroFactura", fact.getNumeroFactura());
			obj.put("fecha", fact.getFecha());
			obj.put("concepto", fact.getConcepto());
			obj.put("nombreRestaurante", fact.getNombreRestaurante());
			obj.put("nombreCliente", fact.getNombreCliente());
			obj.put("totalFactura", fact.getTotalFactura());
			json.put(obj);

		}
		j.put("factura", json);
		return j.toString();
	}
	
	

	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;

}
