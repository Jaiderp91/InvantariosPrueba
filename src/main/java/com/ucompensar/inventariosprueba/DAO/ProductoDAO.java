package com.ucompensar.inventariosprueba.DAO;

import com.ucompensar.inventariosprueba.model.entity.Productos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import java.util.List;

public class ProductoDAO {

    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    public boolean crear(Productos producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(producto);
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

    public List<Productos> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Productos p", Productos.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Productos obtenerPorId(Long idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Productos.class, idProducto);
        } finally {
            em.close();
        }
    }

    public boolean actualizar(Productos producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
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

    public boolean eliminar(Long idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Productos p = em.find(Productos.class, idProducto);
            if (p != null) {
                em.remove(p);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}