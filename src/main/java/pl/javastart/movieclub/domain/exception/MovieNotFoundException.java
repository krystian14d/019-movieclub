package pl.javastart.movieclub.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieNotFoundException extends IOException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
