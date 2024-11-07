package com.ccsw.ludoteca.service.impl;

import com.ccsw.ludoteca.entity.CategoryEntity;
import com.ccsw.ludoteca.model.CategoryDto;
import com.ccsw.ludoteca.repository.CategoryRepository;
import com.ccsw.ludoteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return (List<CategoryEntity>) this.categoryRepository.findAll();
    }

    @Override
    public void save(CategoryDto categoryDto) {
        CategoryEntity category = new CategoryEntity();

        category.setName(categoryDto.getName());

        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, CategoryDto categoryDto) {
        CategoryEntity category = categoryRepository.findById(id).orElse(null);;

        category.setName(categoryDto.getName());
        
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (categoryRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        categoryRepository.deleteById(id);
    }
}
