package co.com.vulky.infraestructure.service.impl;

import co.com.vulky.application.config.security.JwtUtils;
import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.domain.core.Authentication;
import co.com.vulky.infraestructure.data.repository.AuthenticationRepository;
import co.com.vulky.infraestructure.dto.AuthenticationResponseDto;
import co.com.vulky.infraestructure.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private PasswordEncoder encoder;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     JwtUtils jwtUtils, PasswordEncoder encoder) {

        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Override
    public AuthenticationResponseDto authenticate(Authentication dataAuth) throws AppVulkyException {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dataAuth.getUsername(), dataAuth.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticationResponseDto response = AuthenticationResponseDto.builder().build();

        response.setToken(jwtUtils.generateJwtToken(authentication));
        response.setUsercode("prueba");

        return response;
    }

}
