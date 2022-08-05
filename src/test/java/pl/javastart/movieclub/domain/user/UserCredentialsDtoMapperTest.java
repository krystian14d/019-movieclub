package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.Test;
import pl.javastart.movieclub.domain.user.dto.UserCredentialsDto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsDtoMapperTest {

    @Test
    void itShouldMapUserToDto() {
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
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);

        //when
        UserCredentialsDto underTest = UserCredentialsDtoMapper.map(user);

        //then
        assertThat(underTest)
                .usingRecursiveComparison()
                .ignoringFields("id", "roles")
                .isEqualTo(user);

        assertThat(underTest.getRoles()).contains(userRole.getName());

    }
}