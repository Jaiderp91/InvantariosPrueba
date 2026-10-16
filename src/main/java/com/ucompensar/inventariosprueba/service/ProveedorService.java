package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DTO.ProveedorDTO;
import com.ucompensar.inventariosprueba.model.entity.Proveedores;
import com.ucompensar.inventariosprueba.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    public List<ProveedorDTO> listarTodosDTO() {
        EntityManager em = JPAUtil.getEntityManager();
        List<ProveedorDTO> lista = new ArrayList<>();
        try {
            List<Proveedores> proveedores = em.createQuery("SELECT p FROM Proveedores p", Proveedores.class).getResultList();
            for (Proveedores p : proveedores) {
                lista.add(new ProveedorDTO(
                        p.getIdProveedor(),
                        p.getNombre(),
                        p.getContacto(),
                        p.getTelefono()
                ));
            }
        } finally {
            em.close();
        }
        return lista;
    }

    public void crear(ProveedorDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Proveedores prov = new Proveedores();
            prov.setNombre(dto.getNombre());
            prov.setContacto(dto.getContacto());
            prov.setTelefono(dto.getTelefono());
            em.persist(prov);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(ProveedorDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Proveedores prov = em.find(Proveedores.class, dto.getId());
            if (prov != null) {
                prov.setNombre(dto.getNombre());
                prov.setContacto(dto.getContacto());
                prov.setTelefono(dto.getTelefono());
                em.merge(prov);
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
            Proveedores prov = em.find(Proveedores.class, id);
            if (prov != null) em.remove(prov);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}