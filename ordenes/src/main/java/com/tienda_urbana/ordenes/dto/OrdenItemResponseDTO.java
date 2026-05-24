package com.tienda_urbana.ordenes.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenItemResponseDTO {

    private List<CarritoItemDTO> items;
}
