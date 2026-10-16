package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.DetallePedidoDTO;
import com.ucompensar.inventariosprueba.service.DetallePedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/detalles-pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetallePedidoController {

    private DetallePedidoService detallePedidoService = new DetallePedidoService();

    @GET
    @Path("/pedido/{idPedido}")
    public List<DetallePedidoDTO> obtenerPorPedido(@PathParam("idPedido") Long idPedido) {
        return detallePedidoService.obtenerPorPedido(idPedido);
    }
}