package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.ProductoDAO;
import com.ucompensar.inventariosprueba.DAO.ProveedorDAO;
import com.ucompensar.inventariosprueba.DTO.ProductoDTO;
import com.ucompensar.inventariosprueba.model.entity.Productos;
import com.ucompensar.inventariosprueba.model.entity.Proveedores;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private ProductoDAO productoDAO = new ProductoDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    public boolean crearProducto(ProductoDTO dto) {
        Productos prod = new Productos();
        prod.setNombre(dto.getNombre());
        prod.setDescripcion(dto.getDescripcion());
        prod.setPrecio(dto.getPrecio());
        prod.setStock(dto.getStock());

        if (dto.getIdProveedor() != null) {
            Proveedores prov = proveedorDAO.obtenerPorId(dto.getIdProveedor());
            prod.setProveedor(prov);
        }

        return productoDAO.crear(prod);
    }

    public List<ProductoDTO> listarProductos() {
        List<Productos> lista = productoDAO.obtenerTodos();
        List<ProductoDTO> dtos = new ArrayList<>();
        if (lista != null) {
            for (Productos p : lista) {
                Long idProv = (p.getProveedor() != null) ? p.getProveedor().getIdProveedor() : null;
                dtos.add(new ProductoDTO(p.getIdProducto(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), idProv));
            }
        }
        return dtos;
    }

    public ProductoDTO obtenerPorId(Long id) {
        Productos p = productoDAO.obtenerPorId(id);
        if (p == null) return null;
        Long idProv = (p.getProveedor() != null) ? p.getProveedor().getIdProveedor() : null;
        return new ProductoDTO(p.getIdProducto(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), idProv);
    }
}