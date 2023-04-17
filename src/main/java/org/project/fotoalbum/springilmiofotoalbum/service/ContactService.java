package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.model.ContactMessage;
import org.project.fotoalbum.springilmiofotoalbum.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public ContactMessage createMessage(ContactMessage formMessage){
        ContactMessage messageToDB = new ContactMessage();
        messageToDB.setEmail(formMessage.getEmail());
        messageToDB.setMessage(formMessage.getMessage());
        return contactRepository.save(messageToDB);
    }

    public List<ContactMessage> getAllMessages(){
        return contactRepository.findAll();
    }
}
