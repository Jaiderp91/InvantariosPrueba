package com.ucompensar.inventariosprueba.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucompensar.inventariosprueba.DTO.ProductoDTO;
import com.ucompensar.inventariosprueba.service.ProductoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/productos")
public class ProductoController extends HttpServlet {

    private final ProductoService service = new ProductoService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        mapper.writeValue(resp.getWriter(), service.listarTodosDTO());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ProductoDTO dto = mapper.readValue(req.getInputStream(), ProductoDTO.class);
        service.crear(dto);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ProductoDTO dto = mapper.readValue(req.getInputStream(), ProductoDTO.class);
        service.actualizar(dto);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        service.eliminar(Long.parseLong(id));
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}