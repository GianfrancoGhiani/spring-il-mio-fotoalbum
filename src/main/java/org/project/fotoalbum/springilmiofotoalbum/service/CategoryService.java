package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoriesRepository categoriesRepository;

    public Category getCatById(Integer id){
        return categoriesRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
    }

    public List<Category> getAllCats(){
        return categoriesRepository.findAll();
    }
}
