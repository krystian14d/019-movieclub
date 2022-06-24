package pl.javastart.movieclub.config.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.javastart.movieclub.domain.user.UserService;
import pl.javastart.movieclub.domain.user.dto.UserCredentialsDto;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class CustomUserDetailsServiceTest {

    @Mock
    private UserService userService;

    private CustomUserDetailsService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new CustomUserDetailsService(userService);
    }

    @Test
    void itShouldLoadUserByUsername() {
        //given
        String username = "Jan@example.com";
        String password = "password";
        String role = "USER";
        UserCredentialsDto user = new UserCredentialsDto(username, password, Collections.singleton(role));

        given(userService.findCredentialsByEmail(username)).willReturn(Optional.of(user));

        //when
        UserDetails loadedUser = underTest.loadUserByUsername(username);

        //then
        assertThat(loadedUser).isInstanceOf(UserDetails.class);
        assertThat(loadedUser.getUsername()).isEqualTo(username);
        assertThat(loadedUser.getPassword()).isEqualTo(password);
        assertThat(loadedUser.getAuthorities().toString()).contains(role);
    }

    @Test
    void itShouldThrowExceptionWhenUserNotFound() {
        //given
        String username = "Jan@example.com";
        given(userService.findCredentialsByEmail(username)).willReturn(Optional.empty());
        //when

        //then
        assertThatThrownBy(() -> underTest.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(String.format("User with email %s not found", username));
    }
}