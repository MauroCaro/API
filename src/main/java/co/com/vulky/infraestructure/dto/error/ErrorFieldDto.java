package co.com.vulky.infraestructure.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
public class ErrorFieldDto implements Serializable {

    private final String field;
    private final String message;

}