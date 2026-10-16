package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.Clientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import java.util.List;

public class ClienteDAO {

    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    public boolean crear(Clientes cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
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

    public List<Clientes> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Clientes c", Clientes.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Clientes obtenerPorId(Long idCliente) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Clientes.class, idCliente);
        } finally {
            em.close();
        }
    }
}