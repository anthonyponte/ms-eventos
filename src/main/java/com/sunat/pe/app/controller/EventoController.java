package com.sunat.pe.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunat.pe.app.entities.Evento;
import com.sunat.pe.app.service.IEventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
	@Autowired
	private IEventoService eventoService;

	@Autowired
	private Environment environment;

	@GetMapping
	public Map<String, Object> listarTodos() {
		return Map.of(
				"POD_NAME", environment.getProperty("POD_NAME", "Unknown"),
				"POD_ID", environment.getProperty("POD_ID", "Unkown"),
				"eventos", eventoService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> obtenerPorId(@PathVariable Long id) {
		return eventoService.obtenerPorId(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.guardar(evento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> actualizar(@PathVariable Long id, @RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.actualizar(id, evento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		eventoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
