package pl.javastart.movieclub.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Role not found")
public class RoleNotFoundException extends IOException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}
