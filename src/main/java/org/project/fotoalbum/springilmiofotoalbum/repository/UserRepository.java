package org.project.fotoalbum.springilmiofotoalbum.repository;

import org.project.fotoalbum.springilmiofotoalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByNickname(String nickname);
}
