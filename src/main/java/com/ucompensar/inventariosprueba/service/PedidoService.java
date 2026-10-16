package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.ClienteDAO;
import com.ucompensar.inventariosprueba.DAO.PedidoDAO;
import com.ucompensar.inventariosprueba.DTO.DetallePedidoDTO;
import com.ucompensar.inventariosprueba.DTO.PedidoDTO;
import com.ucompensar.inventariosprueba.model.entity.Clientes;
import com.ucompensar.inventariosprueba.model.entity.DetallesPedidos;
import com.ucompensar.inventariosprueba.model.entity.Pedidos;
import com.ucompensar.inventariosprueba.model.entity.Productos;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public boolean registrarPedidoConStock(PedidoDTO pedidoDTO, List<DetallePedidoDTO> detallesDTO) {
        Clientes cliente = clienteDAO.obtenerPorId(pedidoDTO.getIdCliente());
        if (cliente == null) {
            return false;
        }

        Pedidos pedido = new Pedidos();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("pendiente");

        List<DetallesPedidos> detalles = new ArrayList<>();
        if (detallesDTO != null) {
            for (DetallePedidoDTO dDto : detallesDTO) {
                DetallesPedidos detalle = new DetallesPedidos();
                Productos p = new Productos();
                p.setIdProducto(dDto.getIdProducto());
                detalle.setProducto(p);
                detalle.setCantidad(dDto.getCantidad());
                detalles.add(detalle);
            }
        }

        return pedidoDAO.registrarPedidoConStock(pedido, detalles);
    }
}