package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // CORREGIDO: Import para BigDecimal

@Entity
@Table(name = "producto") // CORREGIDO: Singular para consistencia
public class Productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    private String nombre;
    private String descripcion;
    
    // CORREGIDO: BigDecimal es el estándar para precios
    private BigDecimal precio; 
    
    private Integer stock;

    // CORREGIDO: Falta la relación con Proveedor según el diagrama
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedores proveedor;

    // Getters y Setters

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    
}