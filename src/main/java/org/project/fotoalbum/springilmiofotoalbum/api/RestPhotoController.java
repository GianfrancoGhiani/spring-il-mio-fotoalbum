package org.project.fotoalbum.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.project.fotoalbum.springilmiofotoalbum.exception.PhotoNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.model.ContactMessage;
import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.project.fotoalbum.springilmiofotoalbum.service.CategoryService;
import org.project.fotoalbum.springilmiofotoalbum.service.ContactService;
import org.project.fotoalbum.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin
public class RestPhotoController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PhotoService photoService;
    @Autowired
    ContactService contactService;

    @GetMapping
    public List<Photo> getAllPhotos(@RequestParam(name = "name", required = false)Optional<String> name){
        if (name.isPresent()){
            return photoService.getVisiblePhotoFiltered(name.get());
        }
        return photoService.getVisiblePhoto();
    }
    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCats();
    }

    @GetMapping("/{id}")
    public Photo getPhoto(@PathVariable("id")Integer id){

        try {
            Photo photo = photoService.getVisibleSinglePhoto(id);
            return photo;
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PostMapping("/contacts")
    public Map<String, String> sendMessage(@Valid @RequestBody ContactMessage form, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError err : errors) {
                response.put(err.getField(), err.getDefaultMessage());
            }
            response.put("success", "false");
        } else {
            contactService.createMessage(form);
            response.put("success", "true");
        }
        return response;
    }

}
