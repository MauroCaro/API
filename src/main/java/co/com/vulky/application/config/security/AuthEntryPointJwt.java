package co.com.vulky.application.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private Environment message;

    @Autowired
    public AuthEntryPointJwt (Environment message) {
        this.message = message;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        if (authException instanceof  BadCredentialsException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, this.message.getProperty("message.error.auth.invalid.credentials"));
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, this.message.getProperty("message.error.auth.notAuthorized"));
        }
    }


}
