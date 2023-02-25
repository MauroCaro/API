package co.com.vulky.infraestructure.exception;

import co.com.vulky.domain.exception.AppErrorException;
import co.com.vulky.infraestructure.dto.error.ErrorDto;
import co.com.vulky.infraestructure.dto.error.ErrorMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class AppGlobalException extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AppGlobalException.class);

    @Autowired
    private Environment mensaje;

    @ExceptionHandler(AppErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handlerAppErrorException(AppErrorException exception) throws IOException {
        log.error(exception.getMessage(), exception);
        return new ErrorMessageDto("Error", exception.getMessage());
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorDto error = new ErrorDto(this.mensaje.getProperty("message.error.exception.fields"));
        for (FieldError fieldError: fieldErrors) {
            error.add(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<Object>(error, headers, status);
    }

}