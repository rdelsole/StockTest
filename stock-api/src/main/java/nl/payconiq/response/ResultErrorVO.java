package nl.payconiq.response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ResultErrorVO {

    private HttpStatus status;
    private String message;
    private Set<String> errors;

    public ResultErrorVO(HttpStatus status, String message, List<FieldError> fieldErrors) {
        this.status = status;
        this.message = message;

        errors = new HashSet<>();

        if (fieldErrors != null) {
            fieldErrors.stream()
                    .forEach(fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
        }

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }
}
