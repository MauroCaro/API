package co.com.vulky.infraestructure.data.repository.impl;

import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.data.entity.AuthenticationEntity;
import co.com.vulky.infraestructure.data.repository.AuthenticationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationRepositoryImpl implements AuthenticationRepository, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws AppVulkyException {
        if ("prueba".equals(username)) {
                return AuthenticationEntity.builder().userId(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c")).username("prueba").password("$2a$10$XyfRKDcSc81vYCC2oaLdLuR81NP0e/Py5pfRgAJF4MEAkdjjO/JX6").build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public AuthenticationEntity getUserByUsername(String username) throws AppVulkyException {
        return AuthenticationEntity.builder().userId(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c")).username("prueba").password("$2a$10$XyfRKDcSc81vYCC2oaLdLuR81NP0e/Py5pfRgAJF4MEAkdjjO/JX6").build();
    }

}
