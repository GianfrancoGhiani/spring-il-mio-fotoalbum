package org.project.fotoalbum.springilmiofotoalbum.controller;

import org.project.fotoalbum.springilmiofotoalbum.service.CategoryService;
import org.project.fotoalbum.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(){
        return "/photos/index";
    }
}
