package co.com.vulky.domain.core;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authentication implements Serializable {

    private String username;
    private String password;
}