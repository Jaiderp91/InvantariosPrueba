package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.DetallesPedidos;
import com.ucompensar.inventariosprueba.model.entity.HistorialEntregas;
import com.ucompensar.inventariosprueba.model.entity.Pedidos;
import com.ucompensar.inventariosprueba.model.entity.Productos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioPU");

    public boolean registrarPedidoConStock(Pedidos pedido, List<DetallesPedidos> detalles) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Validar stock suficiente
            for (DetallesPedidos detalle : detalles) {
                Productos prod = em.find(Productos.class, detalle.getProducto().getIdProducto());
                if (prod == null || prod.getStock() < detalle.getCantidad()) {
                    throw new Exception("Stock insuficiente para el producto ID: " + detalle.getProducto().getIdProducto());
                }
            }

            // 2. Registrar pedido
            pedido.setFechaPedido(LocalDateTime.now());
            pedido.setEstado("pendiente");
            em.persist(pedido);

            // 3. Procesar detalles y DESCONTAR STOCK
            for (DetallesPedidos detalle : detalles) {
                Productos prod = em.find(Productos.class, detalle.getProducto().getIdProducto());
                
                // Descuento automático
                prod.setStock(prod.getStock() - detalle.getCantidad());
                em.merge(prod);

                detalle.setPedido(pedido);
                detalle.setPrecioUnitario(prod.getPrecio());
                em.persist(detalle);
            }

            // 4. Registrar entrega inicial
            HistorialEntregas historial = new HistorialEntregas();
            historial.setPedido(pedido);
            historial.setFechaEntrega(LocalDateTime.now());
            historial.setEstadoEntrega("pendiente");
            em.persist(historial);

            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Pedidos> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedidos p", Pedidos.class).getResultList();
        } finally {
            em.close();
        }
    }
}