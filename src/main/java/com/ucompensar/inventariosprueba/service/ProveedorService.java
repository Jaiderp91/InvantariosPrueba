package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.ProveedorDAO;
import com.ucompensar.inventariosprueba.DTO.ProveedorDTO;
import com.ucompensar.inventariosprueba.model.entity.Proveedores;
import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public boolean crearProveedor(ProveedorDTO dto) {
        Proveedores p = new Proveedores();
        p.setNombre(dto.getNombre());
        p.setContacto(dto.getContacto());
        p.setTelefono(dto.getTelefono());
        return proveedorDAO.crear(p);
    }

    public List<ProveedorDTO> listarProveedores() {
        List<Proveedores> lista = proveedorDAO.obtenerTodos();
        List<ProveedorDTO> dtos = new ArrayList<>();
        if (lista != null) {
            for (Proveedores p : lista) {
                dtos.add(new ProveedorDTO(p.getIdProveedor(), p.getNombre(), p.getContacto(), p.getTelefono()));
            }
        }
        return dtos;
    }

    public ProveedorDTO obtenerPorId(Long id) {
        Proveedores p = proveedorDAO.obtenerPorId(id);
        if (p == null) return null;
        return new ProveedorDTO(p.getIdProveedor(), p.getNombre(), p.getContacto(), p.getTelefono());
    }
}