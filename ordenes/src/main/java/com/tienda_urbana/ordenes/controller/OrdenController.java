package com.tienda_urbana.ordenes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;
import com.tienda_urbana.ordenes.dto.OrdenResponseDTO;
import com.tienda_urbana.ordenes.exception.ErrorResponseDTO;
import com.tienda_urbana.ordenes.service.OrdenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
@Tag(name = "Ordenes controller", description = "Endpoinst relacionados con el microservicios de ordenes")
public class OrdenController {

    private final OrdenService service;
    // ENDPOINT DE PRUEBA || ELIMINAR
    @GetMapping("/{id}")
    public ResponseEntity<OrdenItemResponseDTO> verCarrito(@PathVariable Long id){
        return ResponseEntity.ok(service.verCarrito(id));
    }

    @Operation(summary = "Crear orden a partir de carrito", description = "Se debe pasar el ID del carrito de donde se van a sacar los productos para generar la orden asi como el ID de usuario al que hara referencia la orden")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cuando la orden es creada correctamente se devuelve una respuesta mostrando la informacion de la orden",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = OrdenResponseDTO.class)
            )
        )//agregar respuesta cuando no hay usuario o carrito
    })
    @PostMapping("/carrito/{carritoId}/usuario/{usuarioId}")
    public ResponseEntity<OrdenResponseDTO> crearOrdenDeCarrito(@PathVariable Long carritoId, @PathVariable Long usuarioId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearOrdenDeCarrito(carritoId, usuarioId));
    }

    @Operation(summary = "Obtener orden por su ID", description = "Se pasa el ID de la orden que se quiere obtener por la URL como PathVariable y se devuelve un cuerpo con la informacion de la orden")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cuando la solicitud resulta exitosa se muestra la informacion de la orden",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = OrdenResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID de orden que se quiere obtener no existe se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID de una orden solicitada no existe",
                    value = """
                            {
                                "timestamp": "2026-06-16 22:13:40",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Orden con ID: 2 no existe",
                                "path": "/api/ordenes/orden/2",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<OrdenResponseDTO> verOrden(@PathVariable Long ordenId){
        return ResponseEntity.ok(service.verOrden(ordenId));
    }
}
