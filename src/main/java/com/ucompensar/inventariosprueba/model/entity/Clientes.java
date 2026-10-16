package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Clientes {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    
    private String nombre;
    private String direccion;
    private String telefono; // CORREGIDO: String según diagrama
    
    @Column(name = "email") // El diagrama dice email, tu código decía correo
    private String email; 
    
    // CORREGIDO: La anotación estaba suelta y faltaba la lista
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedidos> pedidos;

    // Getters y Setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    
}