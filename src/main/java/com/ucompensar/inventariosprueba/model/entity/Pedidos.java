package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;
    
    private LocalDateTime fechaPedido;
    
    // CORREGIDO: El diagrama dice "estado", no "pedido"
    private String estado; 

    // CORREGIDO: Falta la relación inversa con DetallePedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallesPedidos> detalles;

    // Getters y Setters

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
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

    public List<DetallesPedidos> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesPedidos> detalles) {
        this.detalles = detalles;
    }

    
}