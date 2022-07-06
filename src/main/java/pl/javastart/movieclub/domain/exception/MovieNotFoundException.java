package pl.javastart.movieclub.domain.exception;

import java.io.IOException;

public class MovieNotFoundException extends IOException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
