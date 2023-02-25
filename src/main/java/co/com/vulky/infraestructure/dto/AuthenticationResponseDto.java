package co.com.vulky.infraestructure.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDto implements Serializable {

    private String usercode;
    private String token;

}
