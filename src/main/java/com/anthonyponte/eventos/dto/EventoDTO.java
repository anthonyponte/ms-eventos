package com.anthonyponte.eventos.dto;

public class EventoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private Integer capacidadMax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Integer capacidadMax) {
        this.capacidadMax = capacidadMax;
    }
}
