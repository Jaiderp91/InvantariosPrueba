package com.ucompensar.inventariosprueba.DTO;

import java.util.List;

public class PedidoCompletoDTO {
    
    private Long idCliente;
    private List<DetallePedidoDTO> detalles;

    // constructor
    public PedidoCompletoDTO(Long idCliente, List<DetallePedidoDTO> detalles) {
        this.idCliente = idCliente;
        this.detalles = detalles;
    }

    // getters and setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    
}