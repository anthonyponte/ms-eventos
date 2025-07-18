package com.anthonyponte.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anthonyponte.eventos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
