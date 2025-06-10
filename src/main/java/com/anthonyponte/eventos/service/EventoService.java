package com.anthonyponte.eventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anthonyponte.eventos.entity.Evento;

@Service
public interface EventoService {
	public List<Evento> listarEventos();

	public Optional<Evento> obtenerEventoPorId(Long id);

	public Evento guardarEvento(Evento evento);

	public Optional<Evento> actualizarEvento(Long id, Evento evento);

	public void eliminarEvento(Long id);

	public boolean existeEventoPorId(Long id);
}
