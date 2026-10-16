package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.ClienteDTO;
import com.ucompensar.inventariosprueba.model.entity.Clientes;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    // LISTAR TODOS (devuelve DTOs)
    public List<ClienteDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<ClienteDTO> lista = new ArrayList<>();
        try {
            List<Clientes> clientes = em.createQuery("SELECT c FROM Clientes c", Clientes.class).getResultList();
            for (Clientes c : clientes) {
                lista.add(new ClienteDTO(
                        c.getIdCliente(),
                        c.getNombre(),
                        c.getDireccion(),
                        c.getTelefono(),
                        c.getEmail()
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    // CREAR
    public void crear(ClienteDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Clientes cliente = new Clientes();
            cliente.setNombre(dto.getNombre());
            cliente.setDireccion(dto.getDireccion());
            cliente.setTelefono(dto.getTelefono());
            cliente.setEmail(dto.getEmail());
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ACTUALIZAR
    public void actualizar(ClienteDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Clientes cliente = em.find(Clientes.class, dto.getId());
            if (cliente != null) {
                cliente.setNombre(dto.getNombre());
                cliente.setDireccion(dto.getDireccion());
                cliente.setTelefono(dto.getTelefono());
                cliente.setEmail(dto.getEmail());
                em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ELIMINAR
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Clientes cliente = em.find(Clientes.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}