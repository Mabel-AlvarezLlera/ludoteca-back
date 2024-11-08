package com.ccsw.ludoteca.category;

import com.ccsw.ludoteca.entity.CategoryEntity;
import com.ccsw.ludoteca.model.CategoryDto;
import com.ccsw.ludoteca.repository.CategoryRepository;
import com.ccsw.ludoteca.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    public static final String CATEGORY_NAME = "CAT1";
    public static final Long EXISTS_CATEGORY_ID = 1L;

    @Test
    public void findAllShouldReturnAllCategories() {
        List<CategoryEntity> list = new ArrayList<>();
        list.add(mock(CategoryEntity.class));

        when(categoryRepository.findAll()).thenReturn(list);

        List<CategoryEntity> categories = categoryService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    @Test
    public void saveNotExistsCategoryShouldInsert() {
        // La categoría que vamos a guardar
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        // Facilita la captura de argumentos de métodos en los mocks, permitiendo comprobar que el método fue
        //  llamado con los valores correctos
        ArgumentCaptor<CategoryEntity> categoryEntity = ArgumentCaptor.forClass(CategoryEntity.class);

        // Llamada al servicio para guardar la categoría
        categoryService.save(categoryDto);

        // Verificar que se hizo la llamada al repositorio
        verify(categoryRepository).save(categoryEntity.capture());

        assertEquals(CATEGORY_NAME, categoryEntity.getValue().getName());
    }

    @Test
    public void updateExistsCategoryIdShouldUpdate() {
        // La categoría que queremos actualizar
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        CategoryEntity category = mock(CategoryEntity.class);
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        categoryService.update(EXISTS_CATEGORY_ID, categoryDto);

        verify(categoryRepository).save(category);
    }

    @Test
    public void deleteExistsCategoryIdShouldDelete() throws Exception {
        CategoryEntity category = mock(CategoryEntity.class);
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        categoryService.delete(EXISTS_CATEGORY_ID);

        verify(categoryRepository).deleteById(EXISTS_CATEGORY_ID);
    }
}
