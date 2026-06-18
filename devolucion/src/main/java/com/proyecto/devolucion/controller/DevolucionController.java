package com.proyecto.devolucion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.devolucion.DTO.DevolucionRequestDTO;
import com.proyecto.devolucion.DTO.DevolucionResponseDTO;
import com.proyecto.devolucion.Exception.ErrorResponseDTO;
import com.proyecto.devolucion.model.Devolucion;
import com.proyecto.devolucion.service.DevolucionService;

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
@Tag(name = "devolucion", description = "endpoint de microservicio devolucion")
@RestController
@RequestMapping("/api/devoluciones")
@RequiredArgsConstructor
public class DevolucionController {
    private final DevolucionService devolucionService;


    @Operation(
    summary = "Obtener devolucion por ID",
    description = "Obtiene la información de una devolucion mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "la devolucion fue encontrada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = DevolucionResponseDTO.class)
            )
        ),
    })
    @GetMapping()
    public ResponseEntity<List<DevolucionResponseDTO>> obtenerDevolucion(){
        return ResponseEntity.ok(devolucionService.obtenerDevolucion());
    }
//---------------------------------------------------------------------------------------------------------------------------- 

    @Operation(
    summary = "Obtener devolucion por ID",
    description = "Obtiene la información de la devolucion mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "la devolucion fue encontrada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = DevolucionResponseDTO.class)
            )
        ),
        @ApiResponse(
        responseCode = "404",
        description = "la devolucion con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando la devolucion no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:20:00",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "devolucion con ID: 100 no existe",
                    "path": "/api/devoluciones/100",
                    "detalles": null
                }
                """
            )
        )
    )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DevolucionResponseDTO> obtenerPorId(@PathVariable Long id){
        return devolucionService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); 
    }
//----------------------------------------------------------------------------------------------------------------------------   

    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "la devolucion fue creada correctamente",
               content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DevolucionResponseDTO.class)
                )
            ),
            @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados son inválidos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la devolucion es inválido",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:30:15",
                        "status": 400,
                        "error": "Bad Request",
                        "mensaje": "los datos no son suficientes o no validados por el sistema",
                        "path": "/api/devoluciones",
                       "detalles": null
                    }
                    """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<DevolucionResponseDTO> crear(@Valid @RequestBody DevolucionRequestDTO dto){
        return ResponseEntity.status(201).body(devolucionService.guardarD(dto));
    }
//---------------------------------------------------------------------------------------------------------------------------- 

    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "la devolucion fue eliminada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de eliminación exitosa",
                    value = "devolucion eliminada correctamente."
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "el devolucion con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la devolucion no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:45:20",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "devolucion con ID: 100 no existe",
                        "path": "/api/devoluciones/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    }) 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
       devolucionService.eliminarDevolucion(id);
       return ResponseEntity.noContent().build();
    } 
//----------------------------------------------------------------------------------------------------------------------------
    @Operation(
            summary = "Actualizar devolucion",
            description = "Actualiza la información completa de una devolucion existente mediante su ID."
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "la devolucion fue actualizada correctamente",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DevolucionResponseDTO.class)
                )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados son inválidos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "la devolucion con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la devolucion no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:45:20",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "devolucion con ID: 100 no existe",
                        "path": "/api/devoluciones/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    })    
    @PutMapping("/{id}")
    public ResponseEntity<DevolucionResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody DevolucionRequestDTO dto){
            return devolucionService.actualizarDevolucion(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
//----------------------------------------------------------------------------------------------------------------------------   

    @Operation(
        summary = "Actualizar estado de devolucion",
        description = "Permite cambiar el estado de una devolucion (EN_PROCESO, APROBADO, RECHAZADO)"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Estado del devolucion actualizada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = DevolucionResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "la devolucion no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "devolucion no encontrada",
                   value = """
                    {
                        "timestamp": "2026-06-17 17:10:00",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "pago con ID: 100 no existe",
                        "path": "/api/devoluciones/100/estado",
                        "detalles": null
                    }
                    """
                )
           )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Estado inválido",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Estado no válido",
                    value = """
                    {
                        "timestamp": "2026-06-17 17:11:00",
                        "status": 400,
                        "error": "Bad Request",
                        "mensaje": "Estado inválido. Valores permitidos: EN_PROCESO, APROBADO, RECHAZADO",
                        "path": "/api/devoluciones/1/estado",
                        "detalles": null
                    }
                    """
                )
            )
        )
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<DevolucionResponseDTO> actualizarEstado(@PathVariable Long id, @RequestBody String estado){
        return devolucionService
        .actualizarEstado(id, estado)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
