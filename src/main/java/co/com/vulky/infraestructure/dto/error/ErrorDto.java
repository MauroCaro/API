package co.com.vulky.infraestructure.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ErrorDto implements Serializable {

    private final String message;
    private List<ErrorFieldDto> errorField;

    public void add(String field, String message) {
        if (errorField == null) {
            errorField = new ArrayList<>();
        }
        errorField.add(new ErrorFieldDto(field, message));
    }

    public ErrorDto(String mensaje) {
        this.message = mensaje;
    }
}
