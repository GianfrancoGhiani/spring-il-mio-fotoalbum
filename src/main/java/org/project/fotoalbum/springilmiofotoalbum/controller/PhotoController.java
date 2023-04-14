package org.project.fotoalbum.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.project.fotoalbum.springilmiofotoalbum.exception.PhotoNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.project.fotoalbum.springilmiofotoalbum.model.RedirectMessage;
import org.project.fotoalbum.springilmiofotoalbum.model.RedirectMessage.RedirectMessageType;
import org.project.fotoalbum.springilmiofotoalbum.service.CategoryService;
import org.project.fotoalbum.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(value = "name", required = false) Optional<String> name){
        if(name.isPresent()){
            model.addAttribute("photos", photoService.getPhotosFilteringByTitle(name.get()));
        } else {
            model.addAttribute("photos", photoService.getAllPhotos());
        }
        return "/photos/index";
    }
    @GetMapping("/create")
    public String goToCreate(Model model){
        model.addAttribute("photo", new Photo());
        model.addAttribute("categories", categoryService.getAllCats());
        return "/photos/editCreate";
    }
    @PostMapping("/create")
    public String createPhotoToDB(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCats());

            return "/photos/editCreate";
        }
        photoService.createPhoto(photo);
        redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.SUCCESS, "Photo "+photo.getTitle()+" created."));
        return "redirect:/photos";
    }
    @GetMapping("/{id}/edit")
    public String goToEdit(@PathVariable("id") Integer id, Model model){
        try {

            model.addAttribute("photo", photoService.getPhotoById(id));
            model.addAttribute("categories", categoryService.getAllCats());

            return "/photos/editCreate";

        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PostMapping("/edit/{id}")
    public String editPhotoToDB(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, @PathVariable("id")Integer id, RedirectAttributes redirectAttributes, Model model){
        if (photo.getId() != id){
            bindingResult.addError(new FieldError("photo", "id", photo.getId(), false, null, null ,"Illegal action: trying to modifying photos changing id" ));
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCats());

            return "/photos/editCreate";
        }
        photoService.updatePhoto(photo,id);
        redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.SUCCESS, "Photo "+photo.getTitle()+" updated."));
        return "redirect:/photos";
    }
    @GetMapping("/delete/{id}")
    public String deletePhoto(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){
        boolean delete = photoService.deleteById(id);
        if (delete){
            redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.SUCCESS, "Photo deleted."));
        } else {
            redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.ERROR, "Cannot delete this photo"));
        }
        return "redirect:/photos";
    }
}
