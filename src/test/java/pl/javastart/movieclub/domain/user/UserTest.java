package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {


    @Test
    void itShouldCreateUser() {
        //given
        Long id = 1L;
        String email = "jan@example.com";
        String password = "password";

        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setName("USER");
        userRole.setDescription("User role description");

        List<UserRole> role = List.of(userRole);

        //when
        User underTest = new User();
        underTest.setId(id);
        underTest.setEmail(email);
        underTest.setPassword(password);
        underTest.setRoles(role);

        //then
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getEmail()).isEqualTo(email);
        assertThat(underTest.getPassword()).isEqualTo(password);
        assertThat(underTest.getRoles()).isEqualTo(role);
    }
}