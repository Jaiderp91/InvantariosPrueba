package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_entregas")
public class HistorialEntregas {

    @Id // CORREGIDO: Faltaba la anotación @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // CORREGIDO: Faltaba generación
    private Long idHistorialEntregas;

    // CORREGIDO: El diagrama indica relación con Pedido, no con Producto
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedidos pedido;
    
    private LocalDateTime fechaEntrega;
    private String estadoEntrega;

    // Getters y Setters

    public Long getIdHistorialEntregas() {
        return idHistorialEntregas;
    }

    public void setIdHistorialEntregas(Long idHistorialEntregas) {
        this.idHistorialEntregas = idHistorialEntregas;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
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