package com.ucompensar.inventariosprueba.DTO;

import java.time.LocalDateTime;

public class HistorialEntregaDTO {
    
    private Long id;
    private Long idPedido;
    private LocalDateTime fechaEntrega;
    private String estadoEntrega;

    // constructor
    public HistorialEntregaDTO(Long id, Long idPedido, LocalDateTime fechaEntrega, String estadoEntrega) {
        this.id = id;
        this.idPedido = idPedido;
        this.fechaEntrega = fechaEntrega;
        this.estadoEntrega = estadoEntrega;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }
    
    
}