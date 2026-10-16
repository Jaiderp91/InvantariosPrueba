package com.ucompensar.inventariosprueba.DTO;

import java.time.LocalDateTime;

public class HistorialEntregaDTO {

    private Long idEntrega;
    private Long idPedido;
    private LocalDateTime fechaEntrega;
    private String estadoEntrega;

    public HistorialEntregaDTO() {
    }

    public HistorialEntregaDTO(Long idEntrega, Long idPedido, LocalDateTime fechaEntrega, String estadoEntrega) {
        this.idEntrega = idEntrega;
        this.idPedido = idPedido;
        this.fechaEntrega = fechaEntrega;
        this.estadoEntrega = estadoEntrega;
    }

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
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