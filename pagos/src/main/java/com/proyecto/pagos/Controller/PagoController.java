package com.proyecto.pagos.Controller;

import java.util.List;

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

import com.proyecto.pagos.DTO.PagoRequestDTO;
import com.proyecto.pagos.DTO.PagoResponseDTO;
import com.proyecto.pagos.Exception.ErrorResponseDTO;
import com.proyecto.pagos.Service.PagoService;

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
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Tag(name = "pagos", description = "endpoint de microservicio pagos")
public class PagoController {
    private final PagoService pagoService;

    @Operation(
    summary = "Obtener pago por ID",
    description = "Obtiene la información de un pago mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "el pago fue encontrado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PagoResponseDTO.class)
            )
        ),
    })
    @GetMapping()
    public ResponseEntity<List<PagoResponseDTO>> obtenerPago(){
        return ResponseEntity.ok(pagoService.obtenerPagos());
    }
//--------------------------------------------------------------------------------------------------------------------------------

    @Operation(
    summary = "Obtener pago por ID",
    description = "Obtiene la información de un pago mediante su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "el pago fue encontrado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PagoResponseDTO.class)
            )
        ),
        @ApiResponse(
        responseCode = "404",
        description = "el pago con el ID solicitado no existe",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponseDTO.class),
            examples = @ExampleObject(
                summary = "Ejemplo cuando el pago no existe",
                value = """
                {
                    "timestamp": "2026-06-17 16:20:00",
                    "status": 404,
                    "error": "Not Found",
                    "mensaje": "pago con ID: 100 no existe",
                    "path": "/api/pagos/100",
                    "detalles": null
                }
                """
            )
        )
    )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPorId(@PathVariable Long id){
        return pagoService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); 
    }
    
//------------------------------------------------------------------------------------------------------------------------------

    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "el pago fue creado correctamente",
               content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PagoResponseDTO.class)
                )
            ),
            @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados son inválidos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el pago es inválido",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:30:15",
                        "status": 400,
                        "error": "Bad Request",
                        "mensaje": "los datos no son suficientes o no validados por el sistema",
                        "path": "/api/pagos",
                       "detalles": null
                    }
                    """
                )
            )
        )
    })
    @PostMapping()
    public ResponseEntity<PagoResponseDTO> crear(@Valid @RequestBody PagoRequestDTO dto){
        return ResponseEntity.status(201).body(pagoService.guardarP(dto));
    }
//------------------------------------------------------------------------------------------------------------------------------
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "el pago fue eliminado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de eliminación exitosa",
                    value = "pago eliminado correctamente."
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "el pago con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el pago no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:45:20",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "pago con ID: 100 no existe",
                        "path": "/api/pagos/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    }) 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
       pagoService.eliminarPago(id);
       return ResponseEntity.noContent().build();
    } 
//------------------------------------------------------------------------------------------------------------------------------
    @Operation(
        summary = "Actualizar pago",
        description = "Actualiza la información completa de un pago existente mediante su ID."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "El pago fue actualizado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PagoResponseDTO.class)
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
            description = "El pago con el ID solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el pago no existe",
                    value = """
                    {
                        "timestamp": "2026-06-17 16:45:20",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "Pago con ID: 100 no existe",
                        "path": "/api/pagos/100",
                        "detalles": null
                    }
                    """
                )
            )
        )
    })
    
     @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody PagoRequestDTO dto){
            return pagoService.actualizarPago(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
//------------------------------------------------------------------------------------------------------------------------------

        @Operation(
        summary = "Actualizar estado del pago",
        description = "Permite cambiar el estado de un pago (EN_PROCESO, APROBADO, RECHAZADO)"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Estado del pago actualizado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = PagoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "El pago no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Pago no encontrado",
                   value = """
                    {
                        "timestamp": "2026-06-17 17:10:00",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "pago con ID: 100 no existe",
                        "path": "/api/pagos/100/estado",
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
                        "path": "/api/pagos/1/estado",
                        "detalles": null
                    }
                    """
                )
            )
        )
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> actualizarEstado(@PathVariable Long id, @RequestBody String estado){
        return pagoService
        .actualizarEstado(id, estado)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
