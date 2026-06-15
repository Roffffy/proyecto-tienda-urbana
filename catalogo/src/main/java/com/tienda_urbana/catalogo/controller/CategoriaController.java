package com.tienda_urbana.catalogo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.catalogo.dto.CategoriaResponseDTO;
import com.tienda_urbana.catalogo.exception.ErrorResponseDTO;
import com.tienda_urbana.catalogo.dto.CategoriaRequestDTO;
import com.tienda_urbana.catalogo.service.CategoriaService;

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
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categoria controller", description = "Endpoints relacionados con las categorias")
public class CategoriaController {

    private final CategoriaService service;
    
    // Metodo para crear categoria
    @Operation(summary = "Creacion de categoria", description = "Solicita los datos del DTO de creacion de categoria y en caso de exito muestra el nombre de la categoria creada")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Esta respuesta se genera cuando la creacion de categoria resulta exitosa mostrando el nombre de la categoria creada",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CategoriaResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "409", description = "Se devuelve esta respuesta cuando la categoria que se intenta crear ya se encuentra en la base de datos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la categoria ya existe",
                    value = """
                            {
                                "timestamp": "2026-06-14 15:23:45",
                                "status": 409,
                                "error": "Conflict",
                                "mensaje": "La categoria 'Bufandas' ya existe",
                                "path": "/api/categorias",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearCategoria(dto));
    }

    // Metodo para editar categoria
    @Operation(summary = "Editar categoria", description = "Este metodo sirve para cambiar el nombre de la categoria mediante un DTO que solicita el nombre de la categoria y pasando el ID de la categoria a cambiar como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se devuelve esta respuesta cuando la modificacion resulta exitosa mostrando el nuevo nombre de la categoria",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CategoriaResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "409", description = "Se devuelve esta respuesta cuando la categoria que se intenta modificar esta intentando usar un nombre que ya se encuentra en la base de datos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la categoria ya existe",
                    value = """
                            {
                                "timestamp": "2026-06-14 15:23:45",
                                "status": 409,
                                "error": "Conflict",
                                "mensaje": "La categoria 'Bufandas' ya existe",
                                "path": "/api/categorias",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> editarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(service.editarCategoria(id, dto));
    }

    // Metodo para ver todas las categorias
    @Operation(summary = "Listar todas las categorias", description = "Muestra todas las categorias existentes de la base de datos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se muestra una lista con todas las categorias que se encuentran en la base de datos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(type = "array", implementation = CategoriaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de la lista de categorias",
                    value = """
                            [
                                {
                                    "nombre": "Buzos"
                                },
                                {
                                    "nombre": "Camisas"
                                },
                                {
                                    "nombre": "Chaquetas"
                                }
                            ]
                            """
                )
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> verCategorias(){
        return ResponseEntity.ok(service.verCategorias());
    }

    // Metodo para buscar categoria por su nombre
    @Operation(summary = "Buscar categoria por su nombre", description = "En este metodo se puede buscar una categoria por su nombre mediante la URL")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se muestra la o las categorias que coinciden con el texto escrito en la URL",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(type = "array", implementation = CategoriaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de categorias encontradas al buscar poler",
                    value = """
                            [
                                {
                                    "nombre": "Poleras"
                                },
                                {
                                    "nombre": "Polerones"
                                }
                            ]
                            """
                )
            )
        )
    })
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarCategoria(@PathVariable String nombre){
        return ResponseEntity.ok(service.buscarCategoria(nombre));
    }

    // Metodo para eliminar categoria
    @Operation(summary = "Eliminar categoria", description = "Este metodo sirve para eliminar categorias pasando su ID por la URL como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cuando una categoria se elimina correctamente se muestra un texto informando que la categoria fue eliminada",
            content = @Content(
                mediaType = MediaType.TEXT_HTML_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de exito en eliminacion de categoria",
                    value = "Categoria eliminada con exito"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando la categoria con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de error cuando el ID no existe en la base de datos",
                    value = """
                            {
                                "timestamp": "2026-06-14 16:05:11",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Categoria con ID: 100 no existe",
                                "path": "/api/categorias/100",
                                "detalles": null
                            }
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "403", description = "Esta respuesta se genera cuando se intenta eliminar la categoria con ID: 1 que es critica para el funcionamiento de la aplicacion",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando se intenta eliminar la categoria con ID: 1",
                    value = """
                            {
                                "timestamp": "2026-06-14 16:13:28",
                                "status": 403,
                                "error": "Forbidden",
                                "mensaje": "La categoria con ID: 1 NO DEBE ser eliminada",
                                "path": "/api/categorias/1",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id){
        service.eliminarCategoria(id);
        return ResponseEntity.ok("Categoria eliminada con exito");
    }
}
