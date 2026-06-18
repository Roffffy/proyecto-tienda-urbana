package com.proyecto.notificaciones.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.notificaciones.DTO.NotificacionRequestDTO;
import com.proyecto.notificaciones.DTO.NotificacionResponseDTO;
import com.proyecto.notificaciones.Exception.ErrorResponseDTO;
import com.proyecto.notificaciones.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@Tag(name = "notificaciones", description = "operaciones CRUD de notificacion de la tienda")
@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    @Operation(
        summary = "obtener notificaciones",
        description = "retorna todas las notificaciones existentes dentro del sistema"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "lista de notificaciones retornadas correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NotificacionResponseDTO.class)
            )
        )
    })
    @GetMapping()
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerNotificacion(){
        return ResponseEntity.ok(notificacionService.obtenerNotificacion());
    }
//----------------------------------------------------------------------------------------------------------------------------
    @Operation(
        summary = "obtener notificaciones por id",
        description = "retorna las notificaciones que son consultadas por el id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "id de la notificacion retornada exitosamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NotificacionResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "si el id de la notificacion consultada no existe se aplicara el error 404, osea no se encontro la notificacion",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando la notificacion no existe",
                    value = """
                            {
                            "timestamp": "2026-06-15 22:30:00",
                            "status" : "404",
                            "error" : "not found",
                            "mensaje" : "notificacion del id no existente",
                            "path" : "/api/notificaciones/10",
                            "detalles" : null
                            }
                            """
                )
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerPorId(@PathVariable Long id){
        return notificacionService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
//----------------------------------------------------------------------------------------------------------------------------
@Operation(
        summary = "crear notificacion",
        description = "crea una notificacion a partir de un evento asociado del sistema"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "la notificacion fue creada exitosamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NotificacionResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "los datos enviados son invalidos o no cumples con las especificaciones requeridas",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando la notificacion no se ingreso la informacion necesaria",
                    value = """
                            {
                            "timestamp" : "2026-06-15 23:45:10",
                            "status" : "400",
                            "error" : "bad request",
                            "mensaje" : "los datos enviados no cumplen con lo exigido",
                            "path" : "/api/notificaciones",
                            "detalles" : null
                            }
                            """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto

    ){
        return ResponseEntity.status(201).body(notificacionService.guardarN(dto));
    }
//----------------------------------------------------------------------------------------------------------------------------
@Operation(
        summary = "eliminar notificacion por su id",
        description = "elimina la notificacion a traves del id sin retornar nada"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "la notificacion se elimino correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "no se puede eliminar una notificacion no existente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando la notificacion no existe",
                    value = """
                            {
                            "timestamp": "2026-06-15 22:30:00",
                            "status" : "404",
                            "error" : "not found",
                            "mensaje" : "notificacion del id no existente",
                            "path" : "/api/notificaciones/10",
                            "detalles" null
                            }
                            """
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(notificacionService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
//----------------------------------------------------------------------------------------------------------------------------
    @Operation(
        summary = "actualizar notificacion",
        description = "actualiza la notificacion existente a traves de su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "notificacion actualizada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = NotificacionResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "los datos enviados son invalidos o no cumplen con lo exigido",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando la notificacion no se proporciona la informacion necesaria",
                    value = """
                            {
                            "timestamp" : "2026-06-15 23:45:10",
                            "status" : "400",
                            "error" : "bad request",
                            "mensaje" : "los datos enviados no cumplen con lo exigido",
                            "path" : "/api/notificaciones/10",
                            "detalles" : null
                            }
                            """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "no se encontro la notificacion existente por id",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando la notificacion no existe",
                    value = """
                            {
                            "timestamp": "2026-06-15 22:30:00",
                            "status" : "404",
                            "error" : "not found",
                            "mensaje" : "notificacion del id no existente",
                            "path" : "/api/notificaciones/10",
                            "detalles" null
                            }
                            """
                )
            )
        )

    })
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody NotificacionRequestDTO dto){

        return  notificacionService.actualizarNotificacion(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
