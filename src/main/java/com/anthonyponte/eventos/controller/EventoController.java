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

import com.anthonyponte.eventos.dto.EventoDTO;
import com.anthonyponte.eventos.entity.Evento;
import com.anthonyponte.eventos.mapper.EventoMapper;
import com.anthonyponte.eventos.service.EventoService;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {
	@Autowired
	private EventoService service;

	@GetMapping
	public List<EventoDTO> listarEventos() {
		return service.listarEventos()
				.stream()
				.map(EventoMapper::toDTO)
				.toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoDTO> obtenerEventoPorId(@PathVariable Long id) {
		Evento evento = service.obtenerEventoPorId(id);
		EventoDTO dto = EventoMapper.toDTO(evento);

		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<?> guardarEvento(@RequestBody EventoDTO eventoDTO) {
		Evento evento = EventoMapper.toEntity(eventoDTO);
		Evento e = service.guardarEvento(evento);
		EventoDTO dto = EventoMapper.toDTO(e);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.build(e.getId());

		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventoDTO> actualizarEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
		Evento evento = EventoMapper.toEntity(eventoDTO);
		Evento e = service.actualizarEvento(id, evento);
		EventoDTO dto = EventoMapper.toDTO(e);

		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public void eliminarEventoPorId(@PathVariable Long id) {
		service.eliminarEventoPorId(id);
	}
}
