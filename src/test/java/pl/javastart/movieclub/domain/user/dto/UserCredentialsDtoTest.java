package pl.javastart.movieclub.domain.user.dto;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsDtoTest {

    @Test
    void itShouldCreateUserCredentialsDto() {
        //given
        String email = "jan@example.com";
        String password = "password";
        Set<String> role = Collections.singleton("USER");

        //when
        UserCredentialsDto underTest = new UserCredentialsDto(
                email,
                password,
                role
        );

        //then
        assertThat(underTest.getEmail()).isEqualTo(email);
        assertThat(underTest.getPassword()).isEqualTo(password);
        assertThat(underTest.getRoles()).isEqualTo(role);
    }
}