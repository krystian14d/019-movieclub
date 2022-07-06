package pl.javastart.movieclub.domain.exception;

import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class UserNotFoundException extends IOException {

    public UserNotFoundException(String message){
        super(message);
    }

}
