package com.anthonyponte.eventos.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.anthonyponte.eventos.dto.EventoDTO;
import com.anthonyponte.eventos.entity.Evento;

public class EventoMapper {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static EventoDTO toDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setNombre(evento.getNombre());
        dto.setDescripcion(evento.getDescripcion());
        dto.setFecha(evento.getFecha().format(formatter));
        dto.setUbicacion(evento.getUbicacion());
        dto.setCapacidadMax(evento.getCapacidadMax());

        return dto;
    }

    public static Evento toEntity(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setId(dto.getId());
        evento.setNombre(dto.getNombre());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFecha(LocalDateTime.parse(dto.getFecha(), formatter));
        evento.setUbicacion(dto.getUbicacion());
        evento.setCapacidadMax(dto.getCapacidadMax());

        return evento;
    }
}
