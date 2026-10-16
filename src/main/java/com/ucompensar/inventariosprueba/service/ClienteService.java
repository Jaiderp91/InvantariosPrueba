package com.ucompensar.inventariosprueba.service;

import com.ucompensar.inventariosprueba.DAO.ClienteDAO;
import com.ucompensar.inventariosprueba.DTO.ClienteDTO;
import com.ucompensar.inventariosprueba.model.entity.Clientes;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public boolean crearCliente(ClienteDTO dto) {
        Clientes cliente = new Clientes();
        cliente.setNombre(dto.getNombre());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        return clienteDAO.crear(cliente);
    }

    public List<ClienteDTO> listarClientes() {
        List<Clientes> lista = clienteDAO.obtenerTodos();
        List<ClienteDTO> dtos = new ArrayList<>();
        if (lista != null) {
            for (Clientes c : lista) {
                dtos.add(new ClienteDTO(c.getIdCliente(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getEmail()));
            }
        }
        return dtos;
    }

    public ClienteDTO obtenerPorId(Long id) {
        Clientes c = clienteDAO.obtenerPorId(id);
        if (c == null) return null;
        return new ClienteDTO(c.getIdCliente(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getEmail());
    }
}