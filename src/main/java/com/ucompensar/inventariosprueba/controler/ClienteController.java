package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.ClienteDTO;
import com.ucompensar.inventariosprueba.service.ClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private ClienteService clienteService = new ClienteService();

    @GET
    public List<ClienteDTO> listar() {
        return clienteService.listarClientes();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        ClienteDTO dto = clienteService.obtenerPorId(id);
        if (dto != null) {
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Cliente no encontrado\"}").build();
    }

    @POST
    public Response crear(ClienteDTO dto) {
        boolean creado = clienteService.crearCliente(dto);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\":\"Cliente creado exitosamente\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"No se pudo crear el cliente\"}").build();
    }
}