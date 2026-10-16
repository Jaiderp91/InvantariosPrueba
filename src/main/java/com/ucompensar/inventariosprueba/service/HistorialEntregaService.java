package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.HistorialEntregaDAO;
import com.ucompensar.inventariosprueba.DTO.HistorialEntregaDTO;
import com.ucompensar.inventariosprueba.model.entity.HistorialEntregas;
import java.util.ArrayList;
import java.util.List;

public class HistorialEntregaService {

    private HistorialEntregaDAO historialDAO = new HistorialEntregaDAO();

    public List<HistorialEntregaDTO> obtenerPorCliente(Long idCliente) {
        List<HistorialEntregas> lista = historialDAO.obtenerPorCliente(idCliente);
        List<HistorialEntregaDTO> dtos = new ArrayList<>();
        if (lista != null) {
            for (HistorialEntregas h : lista) {
                Long idPed = (h.getPedido() != null) ? h.getPedido().getIdPedido() : null;
               
                dtos.add(new HistorialEntregaDTO(h.getIdHistorialEntregas(), idPed, h.getFechaEntrega(), h.getEstadoEntrega()));
            }
        }
        return dtos;
    }
}