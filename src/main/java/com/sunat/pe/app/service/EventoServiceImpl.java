package com.sunat.pe.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunat.pe.app.entities.Evento;
import com.sunat.pe.app.repository.EventoRepository;

@Component
public class EventoServiceImpl implements IEventoService {
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public List<Evento> listarTodos() {
		return eventoRepository.findAll();
	}

	@Override
	public Optional<Evento> obtenerPorId(Long id) {
		return eventoRepository.findById(id);
	}

	@Override
	public Evento guardar(Evento evento) {
		if (eventoRepository.existsByCodigo(evento.getCodigo())) {
			throw new IllegalArgumentException("El evento ya existe con ese codigo.");
		}
		if (eventoRepository.existsByNombre(evento.getNombre())) {
			throw new IllegalArgumentException("El evento ya existe con ese nombre.");
		}
		return eventoRepository.save(evento);
	}

	@Override
	public Evento actualizar(Long id, Evento eventoActualizado) {
		return eventoRepository.findById(id).map(evento -> {
			evento.setNombre(eventoActualizado.getNombre());
			evento.setDescripcion(eventoActualizado.getDescripcion());
			evento.setFecha(eventoActualizado.getFecha());
			evento.setUbicacion(eventoActualizado.getUbicacion());
			evento.setCapacidadMax(eventoActualizado.getCapacidadMax());
			return eventoRepository.save(evento);
		}).orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
	}

	@Override
	public void eliminar(Long id) {
		eventoRepository.deleteById(id);
	}

	@Override
	public boolean existePorNombre(String nombre) {
		return eventoRepository.existsByNombre(nombre);
	}

	@Override
	public boolean existePorCodigo(String codigo) {
		return eventoRepository.existsByCodigo(codigo);
	}

	@Override
	public Optional<List<Evento>> obtenerPorCodigo(String codigo) {
		return Optional.ofNullable(eventoRepository.findAllByCodigo(codigo));
	}
}