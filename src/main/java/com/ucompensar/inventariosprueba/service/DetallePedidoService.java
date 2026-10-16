package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.DetallePedidoDAO;
import com.ucompensar.inventariosprueba.DTO.DetallePedidoDTO;
import com.ucompensar.inventariosprueba.model.entity.DetallesPedidos;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoService {

    private DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();

    public List<DetallePedidoDTO> obtenerPorPedido(Long idPedido) {
        List<DetallesPedidos> lista = detallePedidoDAO.obtenerPorPedido(idPedido);
        List<DetallePedidoDTO> dtos = new ArrayList<>();
        if (lista != null) {
            for (DetallesPedidos d : lista) {
                Long idPed = (d.getPedido() != null) ? d.getPedido().getIdPedido() : null;
                Long idProd = (d.getProducto() != null) ? d.getProducto().getIdProducto() : null;
                dtos.add(new DetallePedidoDTO(d.getIdDetallePedido(), idPed, idProd, d.getCantidad(), d.getPrecioUnitario()));
            }
        }
        return dtos;
    }
}