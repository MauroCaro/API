package co.com.vulky.domain.core;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

}
