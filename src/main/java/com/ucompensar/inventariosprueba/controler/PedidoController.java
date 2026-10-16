package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.DetallePedidoDTO;
import com.ucompensar.inventariosprueba.DTO.PedidoDTO;
import com.ucompensar.inventariosprueba.service.PedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoController {

    private PedidoService pedidoService = new PedidoService();

    public static class PedidoRequest {
        public PedidoDTO pedido;
        public List<DetallePedidoDTO> detalles;
    }

    @POST
    public Response registrarPedido(PedidoRequest request) {
        if (request == null || request.pedido == null || request.detalles == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\":\"Datos de pedido o detalles incompletos\"}")
                           .build();
        }

        boolean exito = pedidoService.registrarPedidoConStock(request.pedido, request.detalles);
        if (exito) {
            return Response.status(Response.Status.CREATED)
                           .entity("{\"mensaje\":\"Pedido creado y stock actualizado correctamente\"}")
                           .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\":\"No se pudo procesar el pedido (verifique stock suficiente o existencia del cliente)\"}")
                           .build();
        }
    }
}