package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedores {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    
    private String nombre;
    private String contacto;
    private String telefono; // CORREGIDO: Diagrama dice String, no Integer
    
    @OneToMany(mappedBy = "proveedor")
    private List<Productos> productos; // CORREGIDO: Nombre de variable en plural y coherente

    // Getters y Setters

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
    
    
}