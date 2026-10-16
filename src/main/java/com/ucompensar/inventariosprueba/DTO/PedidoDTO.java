package com.ucompensar.inventariosprueba.DTO;

import java.time.LocalDateTime;

public class PedidoDTO {

    private Long idPedido;
    private Long idCliente;
    private LocalDateTime fechaPedido;
    private String estado;

    public PedidoDTO() {
    }

    public PedidoDTO(Long idPedido, Long idCliente, LocalDateTime fechaPedido, String estado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}