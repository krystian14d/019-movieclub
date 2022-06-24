package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRoleTest {

    @Test
    void itShouldCreateUserRole() {
        //given
        Long id = 1L;
        String roleName = "USER";
        String userRoleDescription = "User role description";

        //when
        UserRole underTest = new UserRole();
        underTest.setId(id);
        underTest.setName(roleName);
        underTest.setDescription(userRoleDescription);

        //then
        assertThat(underTest.getId()).isEqualTo(id);
        assertThat(underTest.getName()).isEqualTo(roleName);
        assertThat(underTest.getDescription()).isEqualTo(userRoleDescription);

    }
}