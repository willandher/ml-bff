package com.ml.bff.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurcharseData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id_compra;
    private String titulo;
    private Precio precio;
    private int cantidad;
    private String fecha;
    private String imagen;
    private Vendedor vendedor;
    private Long id_transaccion;
    private Long id_envio;
}
