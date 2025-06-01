package com.sunat.pe.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunat.pe.app.entities.Evento;

@Service
public interface IEventoService {
	public List<Evento> listarTodos();

	public Optional<Evento> obtenerPorId(Long id);

	public Optional<List<Evento>> obtenerPorCodigo(String codigo);

	public Evento guardar(Evento evento);

	public Evento actualizar(Long id, Evento eventoActualizado);

	public void eliminar(Long id);

	public boolean existePorNombre(String nombre);

	public boolean existePorCodigo(String codigo);
}