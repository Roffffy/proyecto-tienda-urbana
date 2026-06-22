package com.proyecto.resena.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.resena.DTO.ResenaRequestDTO;
import com.proyecto.resena.DTO.ResenaResponseDTO;
import com.proyecto.resena.exception.ErrorResponseDTO;
import com.proyecto.resena.service.ResenaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
@Tag(name = "reseña", description = "endpoint de microservicio reseñas")
public class ResenaController {

    private final ResenaService resenaService;

    //obtener en general
    @Operation(
    summary = "Obtener reseña por ID",
    description = "Obtiene la información de una reseña a partir de su identificador."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "La reseña fue encontrada correctamente",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ResenaResponseDTO.class)
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "La reseña solicitada no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la reseña no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:15:30",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "Reseña con ID: 100 no existe",
                    "path": "/api/resenas/100",
                    "detalles": null
                }
                """
            )
        )
    )
})
    @GetMapping()
    public ResponseEntity<List<ResenaResponseDTO>> obtenerResena(){
        return ResponseEntity.ok(resenaService.obtenerResena());
    }
//--------------------------------------------------------------------------------------------------------------------------------
    //obtener por id
    @Operation(
    summary = "Obtener reseña por ID",
    description = "Obtiene la información de una reseña mediante su identificador."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "La reseña fue encontrada correctamente",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ResenaResponseDTO.class)
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "La reseña con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la reseña no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:20:00",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "Reseña con ID: 100 no existe",
                    "path": "/api/resenas/100",
                    "detalles": null
                }
                """
            )
        )
    )
})
    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO> obtenerPorId(@PathVariable Long id){
        return resenaService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
//--------------------------------------------------------------------------------------------------------------------------------
    //crear reseña
    @Operation(
    summary = "Crear reseña",
    description = "Permite registrar una nueva reseña asociada a un usuario y un producto."
)
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "La reseña fue creada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ResenaResponseDTO.class)
            )
        ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados son inválidos",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la clasificación es inválida",
                value = """
                {
                    "timestamp": "2026-06-17 16:30:15",
                    "status": 400,
                    "error": "Bad Request",
                    "mensaje": "La clasificación máxima es de 5 estrellas",
                    "path": "/api/resenas",
                    "detalles": null
                }
                """
            )
        )
    )
})
    @PostMapping
    public ResponseEntity<ResenaResponseDTO> crear(@Valid @RequestBody ResenaRequestDTO dto

    ){
        return ResponseEntity.status(201).body(resenaService.guardarR(dto));
    }
//--------------------------------------------------------------------------------------------------------------------------------
    //eliminar

    @Operation(
    summary = "Eliminar reseña",
    description = "Elimina una reseña existente a partir de su identificador."
)
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "La reseña fue eliminada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de eliminación exitosa",
                    value = "Reseña eliminada correctamente."
                )
            )
        ),
    @ApiResponse(
        responseCode = "404",
        description = "La reseña con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la reseña no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:45:20",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "Reseña con ID: 100 no existe",
                    "path": "/api/resenas/100",
                    "detalles": null
                }
                """
            )
        )
    )
})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(resenaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
//--------------------------------------------------------------------------------------------------------------------------------
    //actualizar
    @Operation(
    summary = "Actualizar reseña",
    description = "Actualiza la información de una reseña existente a partir de su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "La reseña fue actualizada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ResenaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de actualización exitosa",
                    value = """
                    {
                        "id": 1,
                        "clasificacion": 5,
                        "comentario": "Excelente producto, superó mis expectativas.",
                        "creadoEn": "2026-06-17T16:30:45",
                        "usuarioId": 2,
                        "productoId": 10
                    }
                    """
                )
            )
        ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son válidos",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class)
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "La reseña con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la reseña no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:45:20",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "Reseña con ID: 100 no existe",
                    "path": "/api/resenas/100",
                    "detalles": null
                }
                """
            )
        )
    )
})
    @PutMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ResenaRequestDTO dto){

        return resenaService.actualizarResena(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build()); 

    }
}
