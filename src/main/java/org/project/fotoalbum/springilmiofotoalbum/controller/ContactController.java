package org.project.fotoalbum.springilmiofotoalbum.controller;

import org.project.fotoalbum.springilmiofotoalbum.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("list", contactService.getAllMessages());
        return "/messages/index";
    }
}
