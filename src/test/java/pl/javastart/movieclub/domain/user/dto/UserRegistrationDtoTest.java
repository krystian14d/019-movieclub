package pl.javastart.movieclub.domain.user.dto;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserRegistrationDtoTest {

    @Test
    void itShouldCreateUserRegistrationDto() {
        //given
        String email = "jan@example.com";
        String password = "password";
        Set<String> role = Collections.singleton("USER");

        //when
        UserRegistrationDto underTest = new UserRegistrationDto();
        underTest.setEmail(email);
        underTest.setPassword(password);

        //then
        assertThat(underTest.getEmail()).isEqualTo(email);
        assertThat(underTest.getPassword()).isEqualTo(password);
    }
}