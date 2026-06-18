package com.proyecto.envio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.envio.DTO.EnvioRequestDTO;
import com.proyecto.envio.DTO.EnvioResponseDTO;
import com.proyecto.envio.exception.ErrorResponseDTO;
import com.proyecto.envio.service.EnvioService;

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
@RestController
@RequestMapping("/api/envio")
@Tag(name = "envio", description = "endpoint de microservicio envio")
@RequiredArgsConstructor
public class EnvioController {
    private final EnvioService envioService;

    @Operation(
    summary = "Obtener todos los envios",
    description = "Obtiene la información del envio mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "la envio fue encontrada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = EnvioResponseDTO.class)
            )
        ),
    })
    @GetMapping()
    public ResponseEntity<List<EnvioResponseDTO>> obtenerEnvio(){
      return ResponseEntity.ok(envioService.obtenerEnvios());  
    }
//----------------------------------------------------------------------------------------------------------------------------   

    @Operation(
    summary = "Obtener envio por ID",
    description = "Obtiene la información del envio mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "el envio fue encontrado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = EnvioResponseDTO.class)
            )
        ),
        @ApiResponse(
        responseCode = "404",
        description = "el envio con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando el envio no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:20:00",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "envio con ID: 100 no existe",
                    "path": "/api/envio/100",
                    "detalles": null
                }
                """
            )
        )
    )
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> obtenerPorId(@PathVariable Long id){
        return envioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
//---------------------------------------------------------------------------------------------------------------------------- 
    
    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "el envio fue creada correctamente",
               content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = EnvioResponseDTO.class)
                )
            ),
            @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados son inválidos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el envio es inválido",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:30:15",
                        "status": 400,
                        "error": "Bad Request",
                        "mensaje": "los datos no son suficientes o no validados por el sistema",
                        "path": "/api/envio",
                       "detalles": null
                    }
                    """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<EnvioResponseDTO> crear(@Valid @RequestBody EnvioRequestDTO dto){
        return ResponseEntity.status(201).body(envioService.guardarE(dto));
    }
//---------------------------------------------------------------------------------------------------------------------------- 

    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "el envio fue eliminada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "el envio con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando el envio no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:45:20",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "envio con ID: 100 no existe",
                        "path": "/api/envio/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        envioService.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }
//---------------------------------------------------------------------------------------------------------------------------- 
    //obtener solo por orden
        @Operation(
        summary = "obtener envíos por ID de orden",
        description = "obtiene la lista de envíos asociados a una orden específica."
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "envios encontrados correctamente",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = EnvioResponseDTO.class)
                )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "la orden no existe o no tiene envíos asociados",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando no hay envíos para la orden",
                    value = 
                    """
                    {
                        "timestamp": "2026-06-17 17:00:00",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "no existen envíos para la orden ID: 100",
                        "path": "/api/envio/orden/100",
                        "detalles": null
                    }
                    """               
                )
            )
        )
    })
    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<EnvioResponseDTO>>
    obtenerPorOrden(@PathVariable Long ordenId){
        return ResponseEntity.ok(
            envioService.obtenerPorOrdenId(ordenId)
        );
    }
//----------------------------------------------------------------------------------------------------------------------------
        @Operation(
        summary = "actualizar envío",
        description = "actualiza la información de un envío existente según su ID."
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "el envío fue actualizado correctamente",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = EnvioResponseDTO.class)
                )    
        ),
        @ApiResponse(
            responseCode = "400",
            description = "datos inválidos en la actualización",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                        summary = "ejemplo de error de validación",
                    value = 
                    """
                    {
                        "timestamp": "2026-06-17 17:10:00",
                        "status": 400,
                        "error": "Bad Request",
                        "mensaje": "los datos del envío no son válidos",
                        "path": "/api/envio/1",
                        "detalles": null
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "el envío no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "ejemplo cuando el envío no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 17:12:00",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "envío con ID: 100 no existe",
                        "path": "/api/envio/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    }) 
    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody EnvioRequestDTO dto){

            return envioService.actualizarEnvio(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        }
    
}
