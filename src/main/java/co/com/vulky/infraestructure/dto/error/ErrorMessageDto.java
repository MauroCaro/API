package co.com.vulky.infraestructure.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageDto implements Serializable {

    private String error;
    private String message;

}
