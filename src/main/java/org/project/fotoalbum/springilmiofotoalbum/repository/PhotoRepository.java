package org.project.fotoalbum.springilmiofotoalbum.repository;

import org.project.fotoalbum.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    public List<Photo> findByTitleContainingIgnoreCase(String title);

    //API METHODS
    public List<Photo> findByTitleContainingIgnoreCaseAndVisibleIsTrue(String title);
    public List<Photo> findByVisibleIsTrue();
    public Photo findByIdAndVisibleIsTrue(Integer id);
}
