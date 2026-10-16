package com.ucompensar.inventariosprueba.controler;

import com.ucompensar.inventariosprueba.DTO.ProductoDTO;
import com.ucompensar.inventariosprueba.service.ProductoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoController {

    private ProductoService productoService = new ProductoService();

    @GET
    public List<ProductoDTO> listar() {
        return productoService.listarProductos();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        ProductoDTO dto = productoService.obtenerPorId(id);
        if (dto != null) {
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Producto no encontrado\"}").build();
    }

    @POST
    public Response crear(ProductoDTO dto) {
        boolean creado = productoService.crearProducto(dto);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\":\"Producto creado exitosamente\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"No se pudo crear el producto\"}").build();
    }
}