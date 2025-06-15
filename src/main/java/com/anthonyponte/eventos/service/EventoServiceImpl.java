package com.anthonyponte.eventos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.anthonyponte.eventos.entity.Evento;
import com.anthonyponte.eventos.repository.EventoRepository;

@Component
public class EventoServiceImpl implements EventoService {
	@Autowired
	private EventoRepository repository;

	@Override
	public List<Evento> listarEventos() {
		return repository.findAll();
	}

	@Override
	public Evento obtenerEventoPorId(Long id) {
		Evento evento = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

		return evento;
	}

	@Override
	public Evento guardarEvento(Evento evento) {
		return repository.save(evento);
	}

	@Override
	public Evento actualizarEvento(Long id, Evento evento) {
		Evento e = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

		e.setNombre(evento.getNombre());
		e.setDescripcion(evento.getDescripcion());
		e.setFecha(evento.getFecha());
		e.setUbicacion(evento.getUbicacion());
		e.setCapacidadMax(evento.getCapacidadMax());

		return repository.save(e);
	}

	@Override
	public void eliminarEventoPorId(Long id) {
		repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

		repository.deleteById(id);
	}
}
