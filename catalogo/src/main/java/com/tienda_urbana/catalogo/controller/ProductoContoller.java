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

import com.tienda_urbana.catalogo.dto.ProductoCarritoResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoListaResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoRequestDTO;
import com.tienda_urbana.catalogo.dto.ProductoResponseDTO;
import com.tienda_urbana.catalogo.exception.ErrorResponseDTO;
import com.tienda_urbana.catalogo.service.ProductoService;

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
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Producto controller", description = "Endpoints relacionados con los productos")
public class ProductoContoller {

    private final ProductoService service;

    // Metodo para crear producto
    @Operation(summary = "Crear producto", description = "Metodo para la creacion de productos mediante un DTO de creacion de producto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cuando el producto es creado correctamente el cuerpo de la respuesta contiene un DTO con la informacion del producto",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando el producto que se quiere crear contiene un id de categoria que no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando se intenta crear un producto con un ID de categoria inexistente",
                    value = """
                            {
                                "timestamp": "2026-06-15 15:26:56",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Categoria con ID: 1000 no existe",
                                "path": "/api/productos",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearProducto(dto));
    }

    // Metodo para editar producto
    @Operation(summary = "Editar Producto", description = "Este metodo sirve para editar informacion de un producto mediante un DTO que solicita la nueva informacion y pasando el ID del producto a editar por la ruta como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cuando la edicion resulta exitosa, el cuerpo de la respuesta contiene la nueva informacion del producto que se envia mediante un DTO",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID del producto que se quiere modificar no existe en la base de datos se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID del producto que se quiere modificar no existe",
                    value = """
                            {
                                "timestamp": "2026-06-15 15:33:42",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Producto con ID: 200 no existe",
                                "path": "/api/productos/200",
                                "detalles": null
                            }
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando la nueva informacion contiene un id de categoria que no existe",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando la nueva informacion contiene un ID de categoria inexistente",
                    value = """
                            {
                                "timestamp": "2026-06-15 15:26:56",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Categoria con ID: 1000 no existe",
                                "path": "/api/productos",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> editarProducto(@Valid @RequestBody ProductoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.editarProducto(id, dto));
    }

    // Metodo para eliminar producto
    @Operation(summary = "Eliminar producto", description = "Este metodo sirve para eliminar productos pasando su ID por la URL como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cuando el producto se elminar correctamente se devuleve un texto plano confirmando el exito",
            content = @Content(
                mediaType = MediaType.TEXT_PLAIN_VALUE,
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de una eliminacion exitosa",
                    value = "Producto eliminado con exito"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID del producto que se quiere eliminar no existe en la base de datos se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID del producto que se quiere eliminar no existe",
                    value = """
                     {
                        "timestamp": "2026-06-15 15:40:44",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "Producto con ID: 1000 no existe",
                        "path": "/api/productos/1000",
                        "detalles": null
                    }
                            """
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    // Metodo para ver todos los productos
    @Operation(summary = "Listar productos", description = "Muestra todos los productos existentes en la base de datos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Se muestra una lista con los productos que se encuentran en la base de datos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(type = "array", implementation = ProductoListaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de lista de productos",
                    value = """
                    [
                        {
                            "nombre": "Polera básica negra",
                            "precio": 14990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polera estampada rock",
                            "precio": 18990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polera oversize blanca",
                            "precio": 16990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Jeans slim fit azul",
                            "precio": 34990,
                            "categoria": "Pantalones"
                        },
                        {
                            "nombre": "Pantalón cargo verde oliva",
                            "precio": 39990,
                            "categoria": "Pantalones"
                        },
                        {
                            "nombre": "Pantalón de tela beige",
                            "precio": 29990,
                            "categoria": "Pantalones"
                        }
                    ]
                            """
                )
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<ProductoListaResponseDTO>> listarProductos(){
        return ResponseEntity.ok(service.listarProductos());
    }

    // Metodo para ver un producto por su id
    @Operation(summary = "Obtener producto por su ID", description = "Este metodo permite visualizar productos concretos pasando su ID mediante la URL como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "En caso de exito se muestra la informacion del producto solicitado mediante un DTO",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID del producto que se intenta obtener no existe en la base de datos se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID del producto que se quiere obtener no existe",
                    value = """
                    {
                        "timestamp": "2026-06-15 15:51:45",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "Producto con ID: 10000 no existe",
                        "path": "/api/productos/10000",
                        "detalles": null
                    }
                            """
                )
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> verProducto(@PathVariable Long id){
        return ResponseEntity.ok(service.verProducto(id));
    }


    @Operation(summary = "Enviar info producto al carrito", description = "Este endpoint es consumido por el microservicio de carrito al momento de agregar un producto al carrito para solicitar la informacion del producto agregado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Si la solicitud es exitosa se devuelve la informacion del producto con el formato de un DTO",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoCarritoResponseDTO.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Cuando el ID del producto que se intenta solicitar no existe en la base de datos se devuelve una respuesta de error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando el ID del producto que se intenta solicitar no existe",
                    value = """
                    {
                        "timestamp": "2026-06-15 16:04:10",
                        "status": 404,
                        "error": "Not Found",
                        "mensaje": "Producto con ID: 2131 no existe",
                        "path": "/api/productos/2131/enviar-carro",
                        "detalles": null
                    }
                            """
                )
            )
        )
    })
    @GetMapping("/{id}/enviar-carro")
    public ResponseEntity<ProductoCarritoResponseDTO> enviarAlCarrito(@PathVariable Long id){
        return ResponseEntity.ok(service.enviarAlCarrito(id));
    }

    // Metodo para buscar un producto por su nombre
    @Operation(summary = "Buscar por nombres", description = "Este endpoint sirve para buscar productos mediante su nombre devolviendo una lista con los posibles resultados segun el texto ingresado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Si no ocurre ningun problema mediante la busqueda se mostrara una lista con productos que contengan en su nombre el texto ingresado",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(type = "array", implementation = ProductoListaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de una lista de resultados segun busqueda",
                    value = """
                    [
                        {
                            "nombre": "Polera estampada rock",
                            "precio": 18990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polera oversize blanca",
                            "precio": 16990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polerón con capucha gris",
                            "precio": 24990,
                            "categoria": "Polerones"
                        },
                        {
                            "nombre": "Polerón cerrado azul marino",
                            "precio": 22990,
                            "categoria": "Polerones"
                        }
                    ]
                            """
                )
            )
        )
    })
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ProductoListaResponseDTO>> buscarProducto(@PathVariable String nombre){
        return ResponseEntity.ok(service.buscarProducto(nombre));
    }

    // Metodo para listar productos por una misma categoria
    @Operation(summary = "Listar productos por categoria", description = "Se muestra una lista con todos los productos pertenecientes a una misma categoria que se obtiene mediante su ID por la URL como PathVariable")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Si no ocurre ningun problema al momento de realizar la peticion se muestra una lista con todos los productos pertenecientes a la categoria solicitada",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(type = "array", implementation = ProductoListaResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de lista de productos con misma categoria",
                    value = """
                    [
                        {
                            "nombre": "Polera básica negra",
                            "precio": 14990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polera estampada rock",
                            "precio": 18990,
                            "categoria": "Poleras"
                        },
                        {
                            "nombre": "Polera oversize blanca",
                            "precio": 16990,
                            "categoria": "Poleras"
                        }
                    ]
                            """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Se devuelve esta respuesta cuando se intenta enlistar por un ID de categoria que no existe en la base de datos",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponseDTO.class),
                examples = @ExampleObject(
                    summary = "Ejemplo de cuando se intenta buscar productos por una categoria inexistente",
                    value = """
                            {
                                "timestamp": "2026-06-15 19:07:25",
                                "status": 404,
                                "error": "Not Found",
                                "mensaje": "Categoria con ID: 200 no existe",
                                "path": "/api/productos/categoria/200",
                                "detalles": null
                            }
                            """
                )
            )
        )
    })
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoListaResponseDTO>> listarPorCategoria(@PathVariable Long id){
        return ResponseEntity.ok(service.listarPorCategoria(id));
    }

}
