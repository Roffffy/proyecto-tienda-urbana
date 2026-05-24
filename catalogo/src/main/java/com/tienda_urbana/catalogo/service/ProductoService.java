package com.tienda_urbana.catalogo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tienda_urbana.catalogo.dto.ProductoCarritoResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoListaResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoRequestDTO;
import com.tienda_urbana.catalogo.dto.ProductoResponseDTO;
import com.tienda_urbana.catalogo.exception.ElementoNoEncontradoException;
import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.model.Producto;
import com.tienda_urbana.catalogo.repository.CategoriaRepository;
import com.tienda_urbana.catalogo.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository prodRepo;
    private final CategoriaRepository catRepo;

    private ProductoResponseDTO mapToDto(Producto producto) {
        return new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),
                producto.getTalla(), producto.getStock(), producto.getCategoria().getNombre());
    }

    private ProductoListaResponseDTO mapToListDto(Producto producto) {
        return new ProductoListaResponseDTO(producto.getNombre(), producto.getPrecio(),
                producto.getCategoria().getNombre());
    }

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        if (dto.getCategoriaId()==null) {
            dto.setCategoriaId(1L);
        }
        Categoria categoria = catRepo.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ElementoNoEncontradoException("Categoria", dto.getCategoriaId()));
        return mapToDto(prodRepo.save(new Producto(null, dto.getNombre(), dto.getDescripcion(), dto.getPrecio(),
                dto.getTalla(), dto.getStock(), categoria)));
    }

    public ProductoResponseDTO editarProducto(Long id, ProductoRequestDTO dto) {
        Categoria categoria = catRepo.findById(dto.getCategoriaId()).orElseThrow(
                () -> new ElementoNoEncontradoException("Categoria", dto.getCategoriaId()));
        Producto producto = prodRepo.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Producto", id));
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setTalla(dto.getTalla());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);
        return mapToDto(prodRepo.save(producto));
    }

    public void eliminarProducto(Long id) {
        if (!prodRepo.existsById(id)) {
            throw new ElementoNoEncontradoException("Producto", id);
        }
        prodRepo.deleteById(id);
    }

    public List<ProductoListaResponseDTO> listarProductos() {
        return prodRepo.findAll().stream().map(this::mapToListDto).collect(Collectors.toList());
    }

    public ProductoResponseDTO verProducto(Long id) {
        return mapToDto(prodRepo.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Producto", id)));
    }

    private ProductoCarritoResponseDTO mapToCarritoDto(Producto producto){
        return new ProductoCarritoResponseDTO(producto.getId(), producto.getNombre(), producto.getCategoria().getNombre(), producto.getTalla(), producto.getPrecio(), 0);
    }

    public ProductoCarritoResponseDTO enviarAlCarrito(Long id){
        return mapToCarritoDto(prodRepo.findById(id).orElseThrow(()-> new ElementoNoEncontradoException("Producto", id)));
    }

    public List<ProductoListaResponseDTO> buscarProducto(String nombre) {
        return prodRepo.buscarProducto(nombre).stream().map(this::mapToListDto).collect(Collectors.toList());
    }

    public List<ProductoListaResponseDTO> listarPorCategoria(Long id) {
        Categoria categoria = catRepo.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Categoria", id));
        return prodRepo.findByCategoria(categoria).stream().map(this::mapToListDto).collect(Collectors.toList());
    }

    public List<ProductoResponseDTO> obtenerProductosPorIds(List<Long> ids){
        List<Producto> productos = prodRepo.findAllById(ids);

        return productos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

}
