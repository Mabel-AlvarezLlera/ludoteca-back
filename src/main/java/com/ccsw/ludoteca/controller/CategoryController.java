package com.ccsw.ludoteca.controller;

import com.ccsw.ludoteca.entity.CategoryEntity;
import com.ccsw.ludoteca.model.CategoryDto;
import com.ccsw.ludoteca.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name="Category", description="API of Category")
@RequestMapping(value = "/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método para recuperar todas las categorías
     * @return {@link List} de {@link CategoryDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    @GetMapping(path = "")
    public List<CategoryDto> findAll() {
        List<CategoryEntity> categories = categoryService.findAll();

        // tomamos una lista de objetos (categories), transformando cada objeto en un nuevo objeto de tipo
        // CategoryDto utilizando un mapper, y luego guardamos todos los objetos transformados en una nueva lista
        // qué es lo que queremos retornar. En resumen, convertimos de CategoryEntity -> CategoryDto
        return categories.stream() // Se crea un stream que es una secuencia de elementos sobre las que se pueden aplicar operaciones
                .map(e -> modelMapper.map(e, CategoryDto.class)) // Transformamos cada elemento (e) de CategoryRepository a CategoryDto
                .collect(Collectors.toList()); // Todos los elementos transformados los guardamos en una nueva lista
    }

    /**
     * Método para crear una categoría
     * @param categoryDto datos de la entidad
     */
    @Operation(summary = "Save", description = "Create a Category")
    @PostMapping(path = "")
    public void save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    /**
     * Método para actualizar una categoría
     * @param id PK de la entidad
     * @param categoryDto datos de la entidad
     */
    @Operation(summary = "Update", description = "Updates a Category")
    @PutMapping(path = "/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody CategoryDto categoryDto) {
        categoryService.update(id, categoryDto);
    }

    /**
     * Método para borrar una categoría
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        categoryService.delete(id);
    }
}
