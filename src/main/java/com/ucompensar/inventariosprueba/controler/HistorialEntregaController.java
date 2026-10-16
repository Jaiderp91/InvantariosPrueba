package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.HistorialEntregaDTO;
import com.ucompensar.inventariosprueba.service.HistorialEntregaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/historial-entregas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistorialEntregaController {

    private HistorialEntregaService historialEntregaService = new HistorialEntregaService();

    @GET
    @Path("/cliente/{idCliente}")
    public List<HistorialEntregaDTO> obtenerPorCliente(@PathParam("idCliente") Long idCliente) {
        return historialEntregaService.obtenerPorCliente(idCliente);
    }
}