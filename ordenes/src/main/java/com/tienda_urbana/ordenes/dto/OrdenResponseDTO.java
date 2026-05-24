package com.tienda_urbana.ordenes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResponseDTO {

    private Long ordenId;
    private LocalDateTime fecha;
    private int total;
    private String estado;
    private OrdenItemResponseDTO items;
}
