package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.HistorialEntregas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class HistorialEntregaDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioPU");

    public List<HistorialEntregas> obtenerPorCliente(Long idCliente) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT h FROM HistorialEntregas h WHERE h.pedido.cliente.idCliente = :idCliente";
            return em.createQuery(jpql, HistorialEntregas.class)
                     .setParameter("idCliente", idCliente)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}