package co.com.vulky.infraestructure.controller;

import co.com.vulky.domain.core.Authentication;
import co.com.vulky.domain.exception.AppErrorException;
import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.infraestructure.dto.AuthenticationResponseDto;
import co.com.vulky.infraestructure.service.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/authenticate")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private AuthenticationService authenticationService;

    private Environment message;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, Environment message) {
        this.authenticationService = authenticationService;
        this.message = message;
    }

    @ResponseBody
    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@NotNull @Valid @RequestBody Authentication data) {

        AuthenticationResponseDto response = null;
        try {
            response = authenticationService.authenticate(data);
        } catch (AppVulkyException e) {
            System.out.println(e);
            throw new AppErrorException(this.message.getProperty("message.error.auth.invalid.credentials"), e);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}