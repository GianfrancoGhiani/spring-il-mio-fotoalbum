package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.exception.PhotoNotFoundException;
import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.project.fotoalbum.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public Photo getPhotoById(Integer id){
        return photoRepository.findById(id).orElseThrow(()->new PhotoNotFoundException("Photo not found"));
    }

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }
}
