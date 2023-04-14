package org.project.fotoalbum.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.model.ContactMessage;
import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.project.fotoalbum.springilmiofotoalbum.service.CategoryService;
import org.project.fotoalbum.springilmiofotoalbum.service.ContactService;
import org.project.fotoalbum.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
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
        return photoService.getVisibleSinglePhoto(id);
    }
    @PostMapping("/contacts")
    public ContactMessage sendMessage(@Valid @ModelAttribute ContactMessage form) {
        return contactService.createMessage(form);
    }

}
