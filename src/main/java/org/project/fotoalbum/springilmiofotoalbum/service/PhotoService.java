package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.exception.PhotoNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.project.fotoalbum.springilmiofotoalbum.repository.CategoryRepository;
import org.project.fotoalbum.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Photo getPhotoById(Integer id){
        return photoRepository.findById(id).orElseThrow(()->new PhotoNotFoundException("Photo not found"));
    }

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }
    public List<Photo> getPhotosFilteringByTitle(String name){
        return photoRepository.findByTitleContainingIgnoreCase(name);
    }

    public Photo createPhoto(Photo form){
        Photo photoToDB = new Photo();
        photoToDB.setTitle(form.getTitle());
        photoToDB.setDescription(form.getDescription());
        photoToDB.setUrl(form.getUrl());
        photoToDB.setCategories(getCategories(form));
        photoToDB.setVisible(form.isVisible());
        return photoRepository.save(photoToDB);
    }

    private List<Category> getCategories(Photo photo){
        List<Category> categories = new ArrayList<>();
        if (photo.getCategories() != null){

            for (Category c : photo.getCategories()) {
                categories.add(categoryRepository.findById(c.getId()).orElseThrow(()->new CategoryNotFoundException("Category not found")));
            }
        }
        return categories;
    }

    public Photo updatePhoto(Photo form, Integer id){
        Photo photoToDB = getPhotoById(id);
        photoToDB.setTitle(form.getTitle());
        photoToDB.setDescription(form.getDescription());
        photoToDB.setUrl(form.getUrl());
        photoToDB.setCategories(getCategories(form));
        photoToDB.setVisible(form.isVisible());
        return photoRepository.save(photoToDB);
    }
    public boolean deleteById(Integer id){
        getPhotoById(id);
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //API METHODS
    public List<Photo> getVisiblePhoto(){
        return photoRepository.findByVisibleIsTrue();
    }

    public List<Photo> getVisiblePhotoFiltered(String name){
        return photoRepository.findByTitleContainingIgnoreCaseAndVisibleIsTrue(name);
    }

    public Photo getVisibleSinglePhoto(Integer id){
        return photoRepository.findByIdAndVisibleIsTrue(id);
    }

}
