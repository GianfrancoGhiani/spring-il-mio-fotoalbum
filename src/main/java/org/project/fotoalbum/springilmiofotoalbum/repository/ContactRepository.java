package org.project.fotoalbum.springilmiofotoalbum.repository;

import org.project.fotoalbum.springilmiofotoalbum.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactMessage, Integer> {
}
