package co.com.vulky.application.config.security;

import co.com.vulky.infraestructure.data.entity.AuthenticationEntity;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${vulky.app.jwtSecret}")
    private String jwtSecret;

    @Value("${vulky.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Environment message;

    @Autowired
    public JwtUtils (Environment message) {
        this.message = message;
    }

    public String generateJwtToken(Authentication authentication) {

        AuthenticationEntity user = (AuthenticationEntity) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error(this.message.getProperty("message.log.error.invalid.signature"), e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error(this.message.getProperty("message.log.error.invalid.token"), e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(this.message.getProperty("message.log.error.expired.token"), e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(this.message.getProperty("message.log.error.notSupport.token"), e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(this.message.getProperty("message.log.error.empty.claims"), e.getMessage());
        }

        return false;
    }

}
