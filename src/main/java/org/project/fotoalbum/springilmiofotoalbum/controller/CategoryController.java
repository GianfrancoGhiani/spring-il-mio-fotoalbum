package org.project.fotoalbum.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.project.fotoalbum.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.project.fotoalbum.springilmiofotoalbum.model.RedirectMessage;
import org.project.fotoalbum.springilmiofotoalbum.model.RedirectMessage.RedirectMessageType;
import org.project.fotoalbum.springilmiofotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "cat_id", required = false) Optional<Integer> id){
        if (id.isPresent()){
            model.addAttribute("categoryObj", categoryService.getCatById(id.get()));
        } else {
            model.addAttribute("categoryObj", new Category());
        }
        model.addAttribute("list", categoryService.getAllCats());
        return "/categories/index";
    }
    @PostMapping("/create")
    public String createCat(@Valid @ModelAttribute(name = "categoryObj") Category category, BindingResult bindingResult, Model model){
        if (!categoryService.isUnique(category)){
            bindingResult.addError(new FieldError("category", "name", category.getName(), false, null, null, "The name must be unique"));
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("list", categoryService.getAllCats());
            return "/categories/index";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/edit/{id}")
    public String editCat(@Valid @ModelAttribute(name = "categoryObj") Category category, BindingResult bindingResult, @PathVariable("id")Integer id, RedirectAttributes redirectAttributes, Model model){
        if (category.getId() != id){
            bindingResult.addError(new FieldError("category", "name", category.getId(), false, null, null ,"Illegal action: trying to modifying categories changing id" ));
        }
        if (!categoryService.isUnique(category)){
            bindingResult.addError(new FieldError("category", "name", category.getName(), false, null, null, "The name must be unique"));
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("list", categoryService.getAllCats());
            return "/categories/index";
        }
        categoryService.updateCategory(category, id);
        return "redirect:/categories";

    }


    @GetMapping("/delete/{id}")
    public String deleteCat(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){
        try {
            boolean deleted = categoryService.deleteById(id);
            if (deleted){
                redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.SUCCESS, "Category deleted."));
            } else {
                redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.ERROR, "Unable to delete this category"));
            }

        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", new RedirectMessage(RedirectMessageType.ERROR, "Unable to delete this category"));
            return "redirect:/categories";
        }
        return "redirect:/categories";
    }
}
