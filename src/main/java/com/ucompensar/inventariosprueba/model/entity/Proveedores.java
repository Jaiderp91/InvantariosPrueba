/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ucompensar.inventariosprueba.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "proveedor")
public class Proveedores {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    
    private String nombre;
    private String contacto;
    private Integer telefono;
    
    @OneToMany(mappedBy = "proveedor")
    private List<Productos> pproducto;

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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public List<Productos> getPproducto() {
        return pproducto;
    }

    public void setPproducto(List<Productos> pproducto) {
        this.pproducto = pproducto;
    }
    
    
}
