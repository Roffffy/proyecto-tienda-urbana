package com.tienda_urbana.usuarios.controller;


import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.exception.ErrorResponseDTO;
import com.tienda_urbana.usuarios.service.UsuarioService;

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
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints del microservicio de usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Creacion de Usuario", description = "Solicita los datos del DTO de creacion de usuario y en caso de exito retorna los datos del dto de visualizacion de usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Esta respuesta retorna un codigo 201 y el cuerpo con la informacion del usuario cuando es creado correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = VisualizarDatosUsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "Esta respuesta se genera cuando ya se encuentra un usuaio creado con el mismo correo",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el correo que se intenta registrar ya existe",
                    value = """
                            {
                                "timestamp": "2026-06-14 09:21:27",
                                "status": 400,
                                "error": "Bad Request",
                                "mensaje": "El correo 'c.aguirres@gmail.com' ya se encuentra registrado",
                                "path": "/api/usuarios",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<VisualizarDatosUsuarioResponseDTO> crearUsuario(
            @Valid @RequestBody CreacionUsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(service.crearUsuario(dto));
    }

    @Operation(summary = "Ver datos de usuario por su ID", description = "Se pasa el ID del usuario como un PathVariable y en caso de exito retorna los datos de visualizacion de usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se devuelve esta respuesta cuando el usuario existe y la busqueda es exitosa",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = VisualizarDatosUsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando el usuario con el id solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de error cuando el ID no existe en la BD",
                    value = """
                            {
                              "timestamp": "2026-06-14 08:39:37",
                              "status": "404",
                              "error": "Not Found",
                              "mensaje": "Usuario con ID: 100 no existe",
                              "path": "/api/usuarios/mi-cuenta/100",
                              "detalles": null
                            }
                            """
                )
            )
        )
    })
    @GetMapping("/mi-cuenta/{id}")
    public ResponseEntity<VisualizarDatosUsuarioResponseDTO> verDatosUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.verDatosUsuarioPorId(id));
    }

    @Operation(summary = "Modificar contraseña por su ID", description = "Se pasa el ID del usuario como PathVariable y se solicitan los datos del DTO de cambio de contraseña")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se devuelve esta respuesta cuando el cambio de contraseña es exitoso",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando la contraseña es cambiada exitosamente",
                    value = "Contraseña modificada con exito."
                )
            )
        ),
        @ApiResponse(responseCode = "400", description = "Cuando la contraseña solicitada al usuario no coincide con la contraseña actual se devuelve esta respuesta",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando las contrasñeas no coinciden",
                    value = """
                            {
                                "timestamp": "2026-06-14 09:34:32",
                                "status": 400,
                                "error": "Bad Request",
                                "mensaje": "La contraseña antigua no coincide",
                                "path": "/api/usuarios/mi-cuenta/modificar-contrase%C3%B1a/1",
                                "detalles": null
                            }
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando el usuario con el id solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de error cuando el ID no existe en la BD",
                    value = """
                            {
                                "timestamp": "2026-06-14 09:43:06",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Usuario con ID: 100 no existe",
                                "path": "/api/usuarios/mi-cuenta/modificar-contrase%C3%B1a/100",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PutMapping("/mi-cuenta/modificar-contraseña/{id}")
    public ResponseEntity<String> cambiarContraseña(@Valid @RequestBody CambioContraseniaRequestDTO dto, @PathVariable Long id) {
        service.cambiarContraseña(id, dto);
        return ResponseEntity.ok("Contraseña modificada con exito.");
    }

    @Operation(summary = "Cambio de email", description = "Se pasa el ID del usuario como PathVariable y se solicita un nuevo email por el DTO de cambio de email")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "En caso de exito se devuelve la informacion completa del usuario con su nuevo email",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = VisualizarDatosUsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "Cuando el correo nuevo al que se intenta cambiar ya esta registrado se devuelve este error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo cuando el correo que se intenta registrar ya existe",
                    value = """
                            {
                                "timestamp": "2026-06-14 09:21:27",
                                "status": 400,
                                "error": "Bad Request",
                                "mensaje": "El correo 'c.aguirres@gmail.com' ya se encuentra registrado",
                                "path": "/api/usuarios",
                                "detalles": null
                            }
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando el usuario con el id solicitado no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de error cuando el ID no existe en la BD",
                    value = """
                            {
                                "timestamp": "2026-06-14 09:44:18",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Usuario con ID: 100 no existe",
                                "path": "/api/usuarios/mi-cuenta/modificar-email/100",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PutMapping("/mi-cuenta/modificar-email/{id}")
    public ResponseEntity<VisualizarDatosUsuarioResponseDTO> cambiarEmail(@Valid @RequestBody CambioEmailRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.cambiarEmail(id, dto));
    }

}
