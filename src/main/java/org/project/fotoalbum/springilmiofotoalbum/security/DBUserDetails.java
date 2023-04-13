package org.project.fotoalbum.springilmiofotoalbum.security;

import org.project.fotoalbum.springilmiofotoalbum.model.Role;
import org.project.fotoalbum.springilmiofotoalbum.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class DBUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;


    public DBUserDetails(User user) {
        this.username = user.getNickname();
        this.password = user.getPassword();
        this.authorities = new HashSet<>();
        for (Role r : user.getRoles() ) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
