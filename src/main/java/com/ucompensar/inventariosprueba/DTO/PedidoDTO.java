package com.ucompensar.inventariosprueba.DTO;

import java.time.LocalDateTime;

public class PedidoDTO {
    
    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private LocalDateTime fechaPedido;
    private String estado;

    // constructor
    public PedidoDTO(Long id, Long idCliente, String nombreCliente, LocalDateTime fechaPedido, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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