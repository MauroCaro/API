package co.com.vulky.infraestructure.service;

import co.com.vulky.domain.exception.AppVulkyException;
import co.com.vulky.domain.core.Authentication;
import co.com.vulky.infraestructure.dto.AuthenticationResponseDto;

public interface AuthenticationService {

    public AuthenticationResponseDto authenticate(Authentication data) throws AppVulkyException;

}
