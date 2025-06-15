package com.anthonyponte.eventos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthonyponte.eventos.entity.Evento;

@Service
public interface EventoService {
	public List<Evento> listarEventos();

	public Evento obtenerEventoPorId(Long id);

	public Evento guardarEvento(Evento evento);

	public Evento actualizarEvento(Long id, Evento evento);

	public void eliminarEventoPorId(Long id);
}
