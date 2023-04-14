package org.project.fotoalbum.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.project.fotoalbum.springilmiofotoalbum.model.ContactMessage;
import org.project.fotoalbum.springilmiofotoalbum.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class RestContactController {
    @Autowired
    ContactService contactService;

    @PostMapping
    public String sendMessage(@Valid @ModelAttribute(name = "message") ContactMessage form, BindingResult bindingResult){
        String messageToAPI = "";
        if (bindingResult.hasErrors()){
            messageToAPI= "Error sending message";
        } else {
            contactService.createMessage(form);
            messageToAPI= "Message sent";
        }
        return messageToAPI;
    }
}
