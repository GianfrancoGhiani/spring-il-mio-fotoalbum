package org.project.fotoalbum.springilmiofotoalbum.service;

import org.project.fotoalbum.springilmiofotoalbum.model.User;
import org.project.fotoalbum.springilmiofotoalbum.repository.UserRepository;
import org.project.fotoalbum.springilmiofotoalbum.security.DBUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNickname(username);
        if (user.isPresent()){
            return new DBUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User "+ username +" not found");
        }
    }
}
