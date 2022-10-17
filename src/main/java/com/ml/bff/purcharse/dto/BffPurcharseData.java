package com.ml.bff.purcharse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ml.bff.user.dto.Precio;
import com.ml.bff.user.dto.Vendedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BffPurcharseData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_compra;
    private String titulo;
    private Precio precio;
    private int cantidad;
    private String fecha;
    private String imagen;
    private Vendedor vendedor;
    @JsonIgnore
    private Long id_transaccion;
    @JsonIgnore
    private Long id_envio;
    private BffPayment transaction;
    private BffShipment shipment;
}
