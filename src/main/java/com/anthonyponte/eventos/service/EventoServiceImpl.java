package com.anthonyponte.eventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public Optional<Evento> obtenerEventoPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Evento guardarEvento(Evento evento) {
		return repository.save(evento);
	}

	@Override
	public Optional<Evento> actualizarEvento(Long id, Evento evento) {
		return repository.findById(id).map(e -> {
			e.setNombre(evento.getNombre());
			e.setDescripcion(evento.getDescripcion());
			e.setFecha(evento.getFecha());
			e.setUbicacion(evento.getUbicacion());
			e.setCapacidadMax(evento.getCapacidadMax());

			return repository.save(e);
		});
	}

	@Override
	public void eliminarEvento(Long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existeEventoPorId(Long id) {
		return repository.existsById(id);
	}
}