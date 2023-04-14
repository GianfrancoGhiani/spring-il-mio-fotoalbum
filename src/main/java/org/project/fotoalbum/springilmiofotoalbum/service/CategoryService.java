package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoriesRepository;

    public Category getCatById(Integer id){
        return categoriesRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
    }

    public List<Category> getAllCats(){
        List<Category>categories =  categoriesRepository.findAll();
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                if (o1.getId()< o2.getId())
                    return -1;
                else if (o1.getId()>o2.getId()) {
                    return 1;
                }
                return 0;
            }
        });
        return categories;
    }

    public Category createCategory(Category newCat){
        Category catToDB = new Category();
        catToDB.setName(newCat.getName());
        return categoriesRepository.save(catToDB);
    }
    public Category updateCategory(Category category, Integer id){
        Category catToDB = categoriesRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
        catToDB.setName(category.getName());
        return categoriesRepository.save(catToDB);
    }
    public boolean deleteById(Integer id){
        categoriesRepository.findById(id);
        try {
            categoriesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUnique(Category category){
        if (category.getId() != null){
            return !categoriesRepository.existsByNameAndIdNot(category.getName(), category.getId());
        }
        return !categoriesRepository.existsByName(category.getName());
    }
}
