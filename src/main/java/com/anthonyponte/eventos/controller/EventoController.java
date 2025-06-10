package com.anthonyponte.eventos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anthonyponte.eventos.entity.Evento;
import com.anthonyponte.eventos.service.EventoService;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {
	@Autowired
	private EventoService service;

	@Autowired
	private Environment environment;

	@GetMapping
	public Map<String, Object> listarEventos() {
		return Map.of(
				"POD_NAME", environment.getProperty("POD_NAME", "Unknown"),
				"POD_ID", environment.getProperty("POD_ID", "Unkown"),
				"eventos", service.listarEventos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
		return service.obtenerEventoPorId(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Evento> guardarEvento(@RequestBody Evento evento) {
		return ResponseEntity.ok(service.guardarEvento(evento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
		return service.actualizarEvento(id, evento)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
		service.eliminarEvento(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/existe")
	public ResponseEntity<?> existeEventoPorId(@PathVariable Long id) {
		boolean existe = service.existeEventoPorId(id);
		return existe ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}/disminuir-capacidad-max")
	public ResponseEntity<?> disminuirCapacidadMax(@PathVariable Long id) {
		return service.obtenerEventoPorId(id).map(evento -> {
			if (evento.getCapacidadMax() == 0) {
				return ResponseEntity.badRequest()
						.body(Map.of("mensaje", "El evento no tiene capacidad disponible"));
			}
			evento.setCapacidadMax(evento.getCapacidadMax() - 1);
			return ResponseEntity.ok(service.guardarEvento(evento));
		}).orElse(ResponseEntity.notFound().build());
	}

	@PatchMapping("/{id}/aumentar-capacidad-max")
	public ResponseEntity<?> aumentarCapacidadMax(@PathVariable Long id) {
		return service.obtenerEventoPorId(id).map(evento -> {
			evento.setCapacidadMax(evento.getCapacidadMax() + 1);
			return ResponseEntity.ok(service.guardarEvento(evento));
		}).orElse(ResponseEntity.notFound().build());
	}
}
