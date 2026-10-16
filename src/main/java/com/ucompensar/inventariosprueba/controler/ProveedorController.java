package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.ProveedorDTO;
import com.ucompensar.inventariosprueba.service.ProveedorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/proveedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProveedorController {

    private ProveedorService proveedorService = new ProveedorService();

    @GET
    public List<ProveedorDTO> listar() {
        return proveedorService.listarProveedores();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        ProveedorDTO dto = proveedorService.obtenerPorId(id);
        if (dto != null) {
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Proveedor no encontrado\"}").build();
    }

    @POST
    public Response crear(ProveedorDTO dto) {
        boolean creado = proveedorService.crearProveedor(dto);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\":\"Proveedor creado exitosamente\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"No se pudo crear el proveedor\"}").build();
    }
}