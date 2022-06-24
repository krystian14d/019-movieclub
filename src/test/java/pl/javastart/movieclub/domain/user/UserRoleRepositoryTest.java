package pl.javastart.movieclub.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository underTest;

    @Test
    void itShouldFindRoleByName() {
        //given
        String roleName = "USER";
        //when
        Optional<UserRole> userRoleByName = underTest.findByName(roleName);
        //then
        assertThat(userRoleByName).isNotEmpty();
        assertThat(userRoleByName.get().getName()).isEqualTo(roleName);
        assertThat(userRoleByName.get().getDescription()).isNotEmpty();
        assertThat(userRoleByName.get().getId()).isNotNull();
    }
}