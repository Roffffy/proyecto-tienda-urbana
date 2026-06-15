package com.tienda_urbana.catalogo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tienda_urbana.catalogo.dto.CategoriaRequestDTO;
import com.tienda_urbana.catalogo.dto.CategoriaResponseDTO;
import com.tienda_urbana.catalogo.exception.CategoriaYaExistenteException;
import com.tienda_urbana.catalogo.exception.ElementoNoEncontradoException;
import com.tienda_urbana.catalogo.exception.SinCategoriaException;
import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.repository.CategoriaRepository;
import com.tienda_urbana.catalogo.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService {

    private final CategoriaRepository repo;
    private final ProductoRepository prodRepo;

    private CategoriaResponseDTO mapToDto(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getNombre());
    }

    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO dto){
        if (repo.existsByNombre(dto.getNombre())) {
            throw new CategoriaYaExistenteException(dto.getNombre());
        }
        return mapToDto(repo.save(new Categoria(null, dto.getNombre())));
    }

    public CategoriaResponseDTO editarCategoria(Long id, CategoriaRequestDTO dto){
        Categoria categoria = repo.findById(id).orElseThrow(() -> new ElementoNoEncontradoException("Categoria", id));
        if (repo.existsByNombre(dto.getNombre())) {
            throw new CategoriaYaExistenteException(dto.getNombre());
        }
        categoria.setNombre(dto.getNombre());
        return mapToDto(repo.save(categoria));
    }

    public List<CategoriaResponseDTO> verCategorias(){
        return repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<CategoriaResponseDTO> buscarCategoria(String nombre){
        return repo.buscarCategoria(nombre).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void eliminarCategoria(Long id){
        if (!repo.existsById(id)) {
            throw new ElementoNoEncontradoException("Categoria", id);
        }
        if (id == 1) {
            throw new SinCategoriaException();
        }
        
        prodRepo.reasignarCategoria(id);
        repo.deleteById(id);
    }
}
