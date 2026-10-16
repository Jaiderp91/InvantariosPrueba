/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_entregas")
public class HistorialEntregas {

    private Long idHistorialEntregas;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Productos producto;
    
    private LocalDateTime fechaEntrega;
    private String estadoEntrega;

    public Long getIdHistorialEntregas() {
        return idHistorialEntregas;
    }

    public void setIdHistorialEntregas(Long idHistorialEntregas) {
        this.idHistorialEntregas = idHistorialEntregas;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
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
