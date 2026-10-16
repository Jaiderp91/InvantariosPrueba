package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.DetallePedidoDTO;
import com.ucompensar.inventariosprueba.model.entity.DetallesPedidos;
import com.ucompensar.inventariosprueba.model.entity.Pedidos;
import com.ucompensar.inventariosprueba.model.entity.Productos;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoService {

    public List<DetallePedidoDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<DetallePedidoDTO> lista = new ArrayList<>();
        try {
            List<DetallesPedidos> detalles = em.createQuery("SELECT d FROM DetallesPedidos d", DetallesPedidos.class).getResultList();
            for (DetallesPedidos d : detalles) {
                BigDecimal subtotal = d.getProducto().getPrecio().multiply(new BigDecimal(d.getCantidad()));
                lista.add(new DetallePedidoDTO(
                        d.getIdDetallePedido(),
                        d.getPedido().getIdPedido(),
                        d.getProducto().getIdProducto(),
                        d.getProducto().getNombre(),
                        d.getCantidad(),
                        d.getProducto().getPrecio(),
                        subtotal
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    public void crear(DetallePedidoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            DetallesPedidos detalle = new DetallesPedidos();

            Pedidos pedido = em.find(Pedidos.class, dto.getIdPedido());
            Productos producto = em.find(Productos.class, dto.getIdProducto());

            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(dto.getCantidad());

            // LÓGICA DE NEGOCIO: Descontar stock
            if (producto != null && producto.getStock() >= dto.getCantidad()) {
                producto.setStock(producto.getStock() - dto.getCantidad());
                em.merge(producto);
                em.persist(detalle);
            } else {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(DetallePedidoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            DetallesPedidos detalle = em.find(DetallesPedidos.class, dto.getId());
            if (detalle != null) {
                detalle.setCantidad(dto.getCantidad());
                em.merge(detalle);
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
            DetallesPedidos detalle = em.find(DetallesPedidos.class, id);
            if (detalle != null) em.remove(detalle);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}