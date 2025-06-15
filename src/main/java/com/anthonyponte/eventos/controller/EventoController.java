package com.anthonyponte.eventos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anthonyponte.eventos.entity.Evento;
import com.anthonyponte.eventos.service.EventoService;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {
	@Autowired
	private EventoService service;

	@GetMapping
	public List<Evento> listarEventos() {
		return service.listarEventos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
		Evento e = service.obtenerEventoPorId(id);
		return ResponseEntity.ok(e);
	}

	@PostMapping
	public ResponseEntity<?> guardarEvento(@RequestBody Evento evento) {
		Evento e = service.guardarEvento(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.build(e.getId());
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
		Evento e = service.actualizarEvento(id, evento);
		return ResponseEntity.ok(e);
	}

	@DeleteMapping("/{id}")
	public void eliminarEventoPorId(@PathVariable Long id) {
		service.eliminarEventoPorId(id);
	}
}
