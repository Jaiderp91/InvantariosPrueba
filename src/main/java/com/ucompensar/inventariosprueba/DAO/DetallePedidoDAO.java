package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.DetallesPedidos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class DetallePedidoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioPU");

    public List<DetallesPedidos> obtenerPorPedido(Long idPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT d FROM DetallesPedidos d WHERE d.pedido.idPedido = :idPedido";
            return em.createQuery(jpql, DetallesPedidos.class)
                     .setParameter("idPedido", idPedido)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}