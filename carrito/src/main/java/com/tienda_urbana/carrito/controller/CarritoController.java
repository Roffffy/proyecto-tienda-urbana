package com.tienda_urbana.carrito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.carrito.dto.AgregarItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoResponseDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.exception.ErrorResponseDTO;
import com.tienda_urbana.carrito.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
@Tag(name = "Carrito controller", description = "EndPoints del microservicio carrito")
public class CarritoController {

    private final CarritoService service;

    @Operation(summary = "Agregar producto al carrito", description = "Mediante el DTO de agregar item se solicita la cantidad y el ID del producto que se quiere agregar al carrito, adicionalmente se solicita el ID del usuario al que se le va a modificar el carrito por la URL como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Esta respuesta se genera cuando se agrega un producto correctamente. Como cuerpo se muestra la informacion del producto agregado",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID de usuario no existe en la base de datos de retorna una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID de usuario no existe en la base de datos",
                    value = """
                            {
                                "timestamp": "2026-06-16 11:38:49",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Usuario con ID: 100 no existe",
                                "path": "/api/carrito/100",
                                "detalles": null
                            }
                            """
                )
            )
        )//agregar respuesta cuando producto no existe
    })
    @PostMapping("/{usuarioId}")
    public ResponseEntity<ProductoDTO> agregarItem(@PathVariable Long usuarioId, @Valid @RequestBody AgregarItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarItem(usuarioId, dto));
    }

    @Operation(summary = "Ver items del carrito", description = "Este endpoint permite visualizar el contenido del carrito mediante el ID del usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cuando la solicitud resulta exitosa se muestra un DTO que contiene una lista de productos pertenecientes a un carrito",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CarritoResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de lista de productos de un carrito",
                    value = """
                            {
                            "items": [
                                {
                                    "productoId": 1,
                                    "nombre": "Polera básica negra",
                                    "categoria": "Poleras",
                                    "talla": "M",
                                    "precio": 14990,
                                    "cantidad": 10
                                },
                                {
                                    "productoId": 2,
                                    "nombre": "Polera estampada rock",
                                    "categoria": "Poleras",
                                    "talla": "L",
                                    "precio": 18990,
                                    "cantidad": 10
                                },
                                {
                                    "productoId": 3,
                                    "nombre": "Polera oversize blanca",
                                    "categoria": "Poleras",
                                    "talla": "S",
                                    "precio": 16990,
                                    "cantidad": 10
                                }
                            ]
                        }
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando se quiere visualizar un carrito con ID de usuario inexistente se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de peticion con un ID de usuario inexistente",
                    value = """
                            {
                                "timestamp": "2026-06-16 12:25:07",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Usuario con ID: 1000 no existe",
                                "path": "/api/carrito/1000",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> verCarrito(@PathVariable Long usuarioId){
        return ResponseEntity.ok(service.verCarrito(usuarioId));
    }
}
