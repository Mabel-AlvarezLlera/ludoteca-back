package com.ccsw.ludoteca.service;

import com.ccsw.ludoteca.entity.CategoryEntity;
import com.ccsw.ludoteca.model.CategoryDto;
import com.ccsw.ludoteca.repository.CategoryRepository;

import java.util.List;

public interface CategoryService {

    /**
     * Método para recuperar todas las categorías
     * @return {@link List} de {@link CategoryDto}
     */
    List<CategoryEntity> findAll();

    /**
     * Método para crear una categoría
     * @param categoryDto datos de la entidad
     */
    void save(CategoryDto categoryDto);

    /**
     * Método para ctualizar una categoría
     * @param id PK de la entidad
     * @param categoryDto datos de la entidad
     */
    void update(Long id, CategoryDto categoryDto);

    /**
     * Método para borrar una categoría
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;
}
