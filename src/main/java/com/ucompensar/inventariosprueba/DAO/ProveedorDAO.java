package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.Proveedores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import java.util.List;

public class ProveedorDAO {

    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    public boolean crear(Proveedores proveedor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(proveedor);
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

    public List<Proveedores> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Proveedores p", Proveedores.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Proveedores obtenerPorId(Long idProveedor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Proveedores.class, idProveedor);
        } finally {
            em.close();
        }
    }

    public boolean actualizar(Proveedores proveedor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(proveedor);
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
}