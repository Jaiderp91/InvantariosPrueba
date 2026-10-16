package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.PedidoDTO;
import com.ucompensar.inventariosprueba.model.entity.Clientes;
import com.ucompensar.inventariosprueba.model.entity.Pedidos;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    public List<PedidoDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<PedidoDTO> lista = new ArrayList<>();
        try {
            List<Pedidos> pedidos = em.createQuery("SELECT p FROM Pedidos p", Pedidos.class).getResultList();
            for (Pedidos p : pedidos) {
                lista.add(new PedidoDTO(
                        p.getIdPedido(),
                        p.getCliente().getIdCliente(),
                        p.getCliente().getNombre(),
                        p.getFechaPedido(),
                        p.getEstado()
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    public void crear(PedidoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pedidos pedido = new Pedidos();
            
            // Buscar el cliente
            if (dto.getIdCliente() != null) {
                Clientes cli = em.find(Clientes.class, dto.getIdCliente());
                pedido.setCliente(cli);
            }
            
            pedido.setFechaPedido(LocalDateTime.now()); // Fecha automática
            pedido.setEstado("PENDIENTE"); // Estado inicial por defecto

            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(PedidoDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pedidos pedido = em.find(Pedidos.class, dto.getId());
            if (pedido != null) {
                if (dto.getIdCliente() != null) {
                    Clientes cli = em.find(Clientes.class, dto.getIdCliente());
                    pedido.setCliente(cli);
                }
                pedido.setFechaPedido(dto.getFechaPedido());
                pedido.setEstado(dto.getEstado());
                em.merge(pedido);
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
            Pedidos pedido = em.find(Pedidos.class, id);
            if (pedido != null) em.remove(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}