package org.project.fotoalbum.springilmiofotoalbum.repository;

import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    public List<Photo> findByTitleContainingIgnoreCase(String title);
}
