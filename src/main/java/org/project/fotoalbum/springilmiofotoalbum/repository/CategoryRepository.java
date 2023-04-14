package org.project.fotoalbum.springilmiofotoalbum.repository;

import org.project.fotoalbum.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public boolean existsByName(String name);
    public boolean existsByNameAndIdNot(String name, Integer id);
}
