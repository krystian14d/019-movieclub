package pl.javastart.movieclub.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String email;
    private String password;
}
