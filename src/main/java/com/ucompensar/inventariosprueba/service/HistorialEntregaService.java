package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.HistorialEntregaDTO;
import com.ucompensar.inventariosprueba.model.entity.HistorialEntregas;
import com.ucompensar.inventariosprueba.model.entity.Pedidos;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class HistorialEntregaService {

    public List<HistorialEntregaDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<HistorialEntregaDTO> lista = new ArrayList<>();
        try {
            List<HistorialEntregas> entregas = em.createQuery("SELECT h FROM HistorialEntregas h", HistorialEntregas.class).getResultList();
            for (HistorialEntregas h : entregas) {
                lista.add(new HistorialEntregaDTO(
                        h.getIdHistorialEntregas(),
                        h.getPedido().getIdPedido(),
                        h.getFechaEntrega(),
                        h.getEstadoEntrega()
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    public void crear(HistorialEntregaDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            HistorialEntregas entrega = new HistorialEntregas();

            Pedidos pedido = em.find(Pedidos.class, dto.getIdPedido());
            entrega.setPedido(pedido);
            entrega.setFechaEntrega(dto.getFechaEntrega());
            entrega.setEstadoEntrega(dto.getEstadoEntrega());

            em.persist(entrega);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(HistorialEntregaDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            HistorialEntregas entrega = em.find(HistorialEntregas.class, dto.getId());
            if (entrega != null) {
                if (dto.getIdPedido() != null) {
                    Pedidos pedido = em.find(Pedidos.class, dto.getIdPedido());
                    entrega.setPedido(pedido);
                }
                entrega.setFechaEntrega(dto.getFechaEntrega());
                entrega.setEstadoEntrega(dto.getEstadoEntrega());
                em.merge(entrega);
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
            HistorialEntregas entrega = em.find(HistorialEntregas.class, id);
            if (entrega != null) em.remove(entrega);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}