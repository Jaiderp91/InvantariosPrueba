package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.ProductoDTO;
import com.ucompensar.inventariosprueba.model.entity.Productos;
import com.ucompensar.inventariosprueba.model.entity.Proveedores;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public List<ProductoDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<ProductoDTO> lista = new ArrayList<>();
        try {
            List<Productos> productos = em.createQuery("SELECT p FROM Productos p", Productos.class).getResultList();
            for (Productos p : productos) {
                lista.add(new ProductoDTO(
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecio(),
                        p.getStock(),
                        p.getProveedor().getIdProveedor(),
                        p.getProveedor().getNombre()
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    public void crear(ProductoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Productos prod = new Productos();
            prod.setNombre(dto.getNombre());
            prod.setDescripcion(dto.getDescripcion());
            prod.setPrecio(dto.getPrecio());
            prod.setStock(dto.getStock());

            // LÓGICA DE NEGOCIO: Buscar el proveedor por ID
            if (dto.getIdProveedor() != null) {
                Proveedores prov = em.find(Proveedores.class, dto.getIdProveedor());
                prod.setProveedor(prov);
            }

            em.persist(prod);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(ProductoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Productos prod = em.find(Productos.class, dto.getId());
            if (prod != null) {
                prod.setNombre(dto.getNombre());
                prod.setDescripcion(dto.getDescripcion());
                prod.setPrecio(dto.getPrecio());
                prod.setStock(dto.getStock());
                if (dto.getIdProveedor() != null) {
                    Proveedores prov = em.find(Proveedores.class, dto.getIdProveedor());
                    prod.setProveedor(prov);
                }
                em.merge(prod);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Productos prod = em.find(Productos.class, id);
            if (prod != null) em.remove(prod);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}